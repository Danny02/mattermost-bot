/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class Job (
  /* The unique id of the job */
  id: Option[String],
  /* The type of job */
  `type`: Option[String],
  /* The time at which the job was created */
  createAt: Option[Int],
  /* The time at which the job was started */
  startAt: Option[Int],
  /* The last time at which the job had activity */
  lastActivityAt: Option[Int],
  /* The status of the job */
  status: Option[String],
  /* The progress (as a percentage) of the job */
  progress: Option[Int],
  /* A freeform data field containing additional information about the job */
  data: Option[Any]
) extends ApiModel

