package mattermost

import akka.http.scaladsl.model.{StatusCodes, Uri}
import akka.http.scaladsl.server.{Directive1, Route}
import io.circe.{Decoder, Encoder}
import io.circe.generic.extras.{Configuration, ConfiguredJsonCodec}
import mattermost.bot.Mattermost.{complete, formField, formFields, get}

/**
  * @author Daniel Heinrich
  * @since 14.11.2017
  */
package object bot {
  case class Channel(id: String, name: String)

  case class Team(id: String, domain: String)

  case class User(id: String, name: String)

  case class MattermostHeader(channel: Channel,
                              team: Team,
                              user: User,
                              command: String,
                              text: String,
                              token: String,
                              responseUrl: Uri)

  sealed trait ResponseType

  object InChannel extends ResponseType
  object Ephemeral extends ResponseType

  object ResponseType {
    val inchannel = "in_channel"
    val ephemeral = "ephemeral"

    implicit val responseTypeEncoder = Encoder.encodeString.contramap[ResponseType]{
      case InChannel => inchannel
      case Ephemeral => ephemeral
    }

    implicit val responseTypeDecoder = Decoder.decodeString.map{
      case r if r == inchannel => InChannel
      case r if r == ephemeral => Ephemeral
    }
  }

  implicit val configuration: Configuration = Configuration.default.withSnakeCaseMemberNames

  @ConfiguredJsonCodec case class SlashResponseEntity(text: String,
                                                      responseType: ResponseType = InChannel,
                                                      gotoLocation: Option[String] = None,
                                                      username: Option[String] = None,
                                                      iconUrl: Option[String] = None)

  sealed trait MessageTarget
  object DefaultTarget extends MessageTarget
  case class ChannelTarget(name: String) extends MessageTarget
  case class UserTarget(name: String) extends MessageTarget

  object MessageTarget {
    implicit val messageTargetEncoder = Encoder.encodeOption[String].contramap[MessageTarget]{
      case DefaultTarget => None
      case ChannelTarget(channel) => Some(channel)
      case UserTarget(user) => Some('@' + user)
    }
    implicit val messageTargetDecoder = Decoder.decodeOption[String].map{
      case None => DefaultTarget
      case Some(user) if user.startsWith("@") => UserTarget(user.substring(1))
      case Some(channel) => ChannelTarget(channel)
    }
  }

  @ConfiguredJsonCodec case class IncomingHookEntity(text: String,
                                                     channel: MessageTarget = DefaultTarget,
                                                     username: Option[String] = None,
                                                     iconUrl: Option[String] = None)
}
