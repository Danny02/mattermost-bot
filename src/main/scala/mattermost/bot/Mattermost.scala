package mattermost.bot

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.{Marshal, ToEntityMarshaller, ToResponseMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.{Directive1, Directives, Route}
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.Encoder
import mattermost.bot.MonixSupport._
import monix.eval.Task

import scala.concurrent.duration.DurationInt

object Mattermost extends Directives with ErrorAccumulatingCirceSupport {

  val mattermostHeader: Directive1[MattermostHeader] = {
    val channelD = formFields('channel_id, 'channel_name).as(Channel)
    val teamD = formFields('team_id, 'team_domain).as(Team)
    val userD = formFields('user_id, 'user_name).as(User)
    val responseUrl = formField('response_url).as(Uri.apply(_: String))
    val otherD = formFields('command, 'text, 'token)

    (channelD & teamD & userD & otherD & responseUrl).as(MattermostHeader)
  }

  def mattermostRoute(otherRouting: MattermostHeader => Route) = (get | post) {
    mattermostHeader {
      otherRouting
    } ~ complete {
      StatusCodes.BadRequest -> "Request is missing required Mattermost headers"
    }
  }

  def asyncRespond[E: ToEntityMarshaller](e: E)(mm: MattermostHeader): Task[Unit] = Task.deferFutureAction { implicit scheduler =>
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    for {
      request <- Marshal((HttpMethods.POST, mm.responseUrl, e)).to[HttpRequest]
      reponse <- Http().singleRequest(request)
    } yield reponse.discardEntityBytes()
  }

  def ok[E](e: E)(implicit m: ToResponseMarshaller[Tuple2[StatusCode, E]]) = {
    complete(StatusCodes.OK -> e)
  }

  def mattermostSplit(f: MattermostHeader => SlashResponseEntity): Route = {
    get
    mattermostHeader { mm =>
      val mr = f(mm)

      if (mr.text.length <= 4000) {
        implicit val onlyforintellij = implicitly[Encoder[SlashResponseEntity]]
        complete(mr)
      } else {
        val head :: ls = mr.text.grouped(4000).toList.map(t => mr.copy(text = t))

        val t2 = for {
          h <- Task.now(head)
          _ <- Task.traverse(ls)(asyncRespond(_)(mm)).delayExecution(2 seconds).background
        } yield (h)

        ok(t2)
      }
    }
  }

  trait Respondable[E] extends (E => Task[SlashResponseEntity])

  object Respondable {

    private def fromFunc[E](f: E => Task[SlashResponseEntity]) = new Respondable[E] {
      def apply(e: E) = f(e)
    }

    implicit val id: Respondable[SlashResponseEntity] = fromFunc(Task.now)
    implicit val fromString: Respondable[String] = fromFunc(s => Task.now(SlashResponseEntity(s)))

    implicit def fromTask[E](implicit sr: Respondable[E]): Respondable[Task[E]] = fromFunc(_.flatMap(sr))
  }

  def completeMattermost[E](f: => E)(implicit toRes: Respondable[E]): MattermostHeader => Route = { mm =>
    val buffer = 500 milli
    val reqTimeout = 3 seconds
    val asyncTimeout = 30 seconds

    val res = toRes(f)
    val timeoutTask = Task.unit.delayExecution(reqTimeout - buffer)

    val response = Task.chooseFirstOf(res, timeoutTask).flatMap {
      case Left((r, t)) => {
        t.cancel
        Task.now(Some(r))
      }
      case Right((r, _)) => {
        val rf = Task.fromFuture(r)

        val t2 = asyncTimeout - reqTimeout - buffer
        val async = rf.timeoutTo(t2, Task.now(SlashResponseEntity("error: request timed out"))).map(asyncRespond(_)(mm))

        async.background.map(_ => None)
      }
    }

    ok(response)
  }
}
