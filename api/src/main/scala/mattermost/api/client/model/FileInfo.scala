/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class FileInfo (
  /* The unique identifier for this file */
  id: Option[String],
  /* The ID of the user that uploaded this file */
  userId: Option[String],
  /* If this file is attached to a post, the ID of that post */
  postId: Option[String],
  createAt: Option[Int],
  updateAt: Option[Int],
  deleteAt: Option[Int],
  /* The name of the file */
  name: Option[String],
  /* The extension at the end of the file name */
  extension: Option[String],
  /* The size of the file in bytes */
  size: Option[Int],
  /* The MIME type of the file */
  mimeType: Option[String],
  /* If this file is an image, the width of the file */
  width: Option[Int],
  /* If this file is an image, the height of the file */
  height: Option[Int],
  /* If this file is an image, whether or not it has a preview-sized version */
  hasPreviewImage: Option[Boolean]
) extends ApiModel


