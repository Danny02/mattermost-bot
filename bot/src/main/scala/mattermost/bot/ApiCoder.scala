package mattermost.bot

import io.circe.{Decoder, Encoder, HCursor, Json}
import io.circe.generic.semiauto._
import mattermost.api.client.model
import mattermost.api.client.model.UserNotifyProps

/**
  * @author Daniel Heinrich
  * @since 11.01.2018
  */
object ApiCoder {
  private implicit val anyEncoder: Encoder[Any] = (a: Any) => Json.Null
  private implicit val anyDecoder: Decoder[Any] = (c: HCursor) => null

  implicit val userNotifyPropsEncoder: Encoder[UserNotifyProps] = deriveEncoder
  implicit val userNotifyPropsDecoder: Decoder[model.UserNotifyProps] = deriveDecoder

  implicit val userEncoder: Encoder[model.User] = deriveEncoder
  implicit val userDecoder: Decoder[model.User] = deriveDecoder
}
