/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class Reaction (
  /* The ID of the user that made this reaction */
  userId: Option[String],
  /* The ID of the post to which this reaction was made */
  postId: Option[String],
  /* The name of the emoji that was used for this reaction */
  emojiName: Option[String],
  /* The time at which this reaction was made */
  createAt: Option[Int]
) extends ApiModel


