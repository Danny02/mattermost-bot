package mattermost.bot

import java.util.concurrent.TimeoutException

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.{Directive1, HttpApp, Route}
import com.typesafe.config.ConfigFactory
import io.circe.Encoder
import io.circe.generic.extras._
import mattermost.bot.CirceSupport._
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

import scala.concurrent.duration.DurationInt

object WebServer extends HttpApp {

  case class Channel(id: String, name: String)

  case class Team(id: String, domain: String)

  case class User(id: String, name: String)

  case class MattermostHeader(channel: Channel,
                              team: Team,
                              user: User,
                              command: String,
                              responseUrl: String,
                              text: String,
                              token: String)

  val mattermostD: Directive1[MattermostHeader] = {
    val channelD = formFields('channel_id, 'channel_name).as(Channel)
    val teamD = formFields('team_id, 'team_domain).as(Team)
    val userD = formFields('user_id, 'user_name).as(User)
    val otherD = formFields('command, 'response_url, 'text, 'token)

    (channelD & teamD & userD & otherD).as(MattermostHeader)
  }

  val inChannel = "in_channel"
  val ephemeral = "ephemeral"

  implicit val configuration: Configuration = Configuration.default.withSnakeCaseMemberNames

  @ConfiguredJsonCodec case class MattermostResponse(text: String,
                                                     responseType: String = inChannel,
                                                     gotoLocation: Option[String] = None,
                                                     username: Option[String] = None,
                                                     iconUrl: Option[String] = None)

  implicit val onlyforintellij = implicitly[Encoder[MattermostResponse]]

  def respondInChannel(text: String): Route = respondInChannel(Task.now(text))
  def respondInChannel(text: Task[String]): Route = {
    val response = text.map(MattermostResponse(_)).timeout(3 seconds).onErrorHandle{
      case _:TimeoutException => MattermostResponse("error: an timeout occured", responseType = ephemeral)
    }
    complete(StatusCodes.OK -> response.runAsync)
  }

  override def routes: Route = mattermostD { mm =>
    respondInChannel(Task(s"hello ${mm.user.name}").delayExecution(5 seconds))
  }

  //    ((get | post) & mattermostHeader) { mm =>
  //    complete("Hallo World")
  //  }

  def main(args: Array[String]): Unit = {
    val httpConfig = ConfigFactory.load().getConfig("http")

    val interface = httpConfig.getString("interface")
    val port = httpConfig.getInt("port")
    WebServer.startServer(interface, port)
  }
}
