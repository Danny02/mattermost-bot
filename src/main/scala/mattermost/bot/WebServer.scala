package mattermost.bot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.common.EntityStreamingSupport
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{DateTime, HttpMethods, HttpRequest, Uri}
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.http.scaladsl.util.FastFuture.EnhancedFuture
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.Decoder
import io.circe.generic.auto._
import mattermost.bot.Mattermost._
import mattermost.bot.MonixSupport._
import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Pipe

import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.util.Try

object WebServer extends HttpApp with ErrorAccumulatingCirceSupport  {

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
      val mattermostConfig = ConfigFactory.load().getConfig("mattermost")
      val hookUri = Uri(mattermostConfig.getString("incoming"))

      implicit val materializer = ActorMaterializer()

      for {
        request <- Marshal((HttpMethods.POST, hookUri, msg)).to[HttpRequest]
        reponse <- Http().singleRequest(request)
      } yield reponse.discardEntityBytes()
  }

  def main(args: Array[String]): Unit = {
    val httpConfig = ConfigFactory.load().getConfig("http")

    val interface = httpConfig.getString("interface")
    val port = httpConfig.getInt("port")
    WebServer.startServer(interface, port)
  }
}
