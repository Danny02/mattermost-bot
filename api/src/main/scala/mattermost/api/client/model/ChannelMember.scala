/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class ChannelMember (
  channelId: Option[String],
  userId: Option[String],
  roles: Option[String],
  lastViewedAt: Option[Int],
  msgCount: Option[Int],
  mentionCount: Option[Int],
  /* Field only visible to self and admins */
  notifyProps: Option[ChannelNotifyProps],
  lastUpdateAt: Option[Int]
) extends ApiModel


