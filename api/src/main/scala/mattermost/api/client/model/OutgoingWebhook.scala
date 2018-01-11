/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class OutgoingWebhook (
  /* The unique identifier for this outgoing webhook */
  id: Option[String],
  createAt: Option[Int],
  updateAt: Option[Int],
  deleteAt: Option[Int],
  /* The Id of the user who created the webhook */
  creatorId: Option[String],
  /* The ID of the team that the webhook watchs */
  teamId: Option[String],
  /* The ID of a public channel that the webhook watchs */
  channelId: Option[String],
  /* The description for this outgoing webhook */
  description: Option[String],
  /* The display name for this outgoing webhook */
  displayName: Option[String],
  /* List of words for the webhook to trigger on */
  triggerWords: Option[Seq[String]],
  /* When to trigger the webhook, `0` when a trigger word is present at all and `1` if the message starts with a trigger word */
  triggerWhen: Option[Int],
  /* The URLs to POST the payloads to when the webhook is triggered */
  callbackUrls: Option[Seq[String]],
  /* The format to POST the data in, either `application/json` or `application/x-www-form-urlencoded` */
  contentType: Option[String]
) extends ApiModel

