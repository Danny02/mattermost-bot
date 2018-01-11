/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class Body26 (
  /* The channel ID that is being viewed. Use a blank string to indicate that all channels have lost focus. */
  channelId: String,
  /* The channel ID of the previous channel, used when switching channels. Providing this ID will cause push notifications to clear on the channel being switched to. */
  prevChannelId: Option[String]
) extends ApiModel

