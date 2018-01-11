package mattermost.bot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.common.EntityStreamingSupport
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.Uri.Path
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.http.scaladsl.unmarshalling.Unmarshaller
import akka.stream.scaladsl.Flow
import akka.stream.{ActorMaterializer, Materializer}
import akka.util.ByteString
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.auto._
import io.circe.{Decoder, Encoder, HCursor, Json}
import mattermost.api.client.api.UsersApi
import mattermost.api.client.core.{ApiInvoker, ApiRequest}
import mattermost.bot.Mattermost._
import mattermost.bot.ApiCoder._
import mattermost.bot.MonixSupport._
import mattermost.bot.config.{MattermostConfig, ServerConfig}
import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Pipe

import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.util.Try

object WebServer extends HttpApp with ErrorAccumulatingCirceSupport {
  val mmConfig = new MattermostConfig()

  val apiInvoker = ApiInvoker(mmConfig.uri, mmConfig.accessToken)

  def requestApi[A: Encoder : Decoder](r: ApiRequest[A])(route: Task[A] => Route): Route = {
    extractActorSystem {
      implicit as =>
        extractMaterializer {
          implicit m =>
            route(Task.deferFuture(apiInvoker.request(r)))
        }
    }
  }

  implicit val durationDecoder: Decoder[FiniteDuration] = Decoder.decodeString.emapTry { s =>
    Try {
      Some(Duration(s)).collect {
        case d: FiniteDuration => d
      }.get
    }
  }

  case class TimedMessage(delay: FiniteDuration, text: String)

  case class Message(text: String)

  type PipeMessage = (String, Message)
  lazy val (in, out) = Pipe.publish[PipeMessage].multicast(Scheduler(systemReference.get().dispatcher))

  def pushOnPipe(m: PipeMessage): Task[Unit] = Task.fromFuture(in.onNext(m)).map(_ => ())

  val lineSeperator = Flow[ByteString].intersperse(ByteString("\n"))
  implicit val streamingSupport = EntityStreamingSupport.json().withFramingRenderer(lineSeperator)

  override def routes: Route = path("long-answer") {
    mattermostRoute {
      completeMattermost {
        SlashResponseEntity(Array.fill(4001)('a').mkString)
      }
    }
  } ~ path("user" / "email" / Segment) { email =>
    get {
      requestApi(UsersApi.usersEmailEmailGet(email)) { res =>
        complete(StatusCodes.OK -> res.map(_.username))
      }
    }
  } ~ path("chat" / Segment) { room =>
    get {
      complete(out.filter(_._1 == room).map(_._2.text))
    } ~ post {
      entity(as[Message]) { m =>
        complete(pushOnPipe((room, m)))
      }
    }
  } ~ path("message") {
    post {
      extractActorSystem { implicit asystem =>
        entity(as[TimedMessage]) { tm =>
          val msg = IncomingHookEntity(tm.text)
          val postMsg = postToMM(msg).delayExecution(tm.delay)
          val targetTime = DateTime.now + tm.delay.toMillis
          val response = for (_ <- postMsg.background) yield s"will send message at $targetTime"
          complete(response)
        }
      }
    }
  }

  def postToMM(msg: IncomingHookEntity)(implicit as: ActorSystem): Task[Unit] = Task.deferFutureAction {
    implicit scheduler =>
      val hookUri = Uri(mmConfig.uri).withPath(Path / "hooks" / mmConfig.incomingHookToken)

      implicit val materializer = ActorMaterializer()

      for {
        request <- Marshal((HttpMethods.POST, hookUri, msg)).to[HttpRequest]
        reponse <- Http().singleRequest(request)
      } yield reponse.discardEntityBytes()
  }

  def main(args: Array[String]): Unit = {
    val serverConfig = new ServerConfig()
    WebServer.startServer(serverConfig.host, serverConfig.port)
  }
}
