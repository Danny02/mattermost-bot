/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class Compliance (
  id: Option[String],
  createAt: Option[Int],
  userId: Option[String],
  status: Option[String],
  count: Option[Int],
  desc: Option[String],
  `type`: Option[String],
  startAt: Option[Int],
  endAt: Option[Int],
  keywords: Option[String],
  emails: Option[String]
) extends ApiModel


