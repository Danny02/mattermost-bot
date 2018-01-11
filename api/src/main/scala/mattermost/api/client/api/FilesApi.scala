/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import java.io.File

import mattermost.api.client.core._
import mattermost.api.client.model.{AppError, FileInfo, InlineResponse2006}

object FilesApi {

  /**
   * Gets a file that has been uploaded previously. ##### Permissions Must have &#x60;read_channel&#x60; permission or be uploader of the file. 
   * 
   * Expected answers:
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param fileId The ID of the file to get
   */
  def filesFileIdGet(fileId: String): ApiRequest[Unit] =
    ApiRequest[Unit](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/files/{file_id}", "application/json")
      .withPathParam("file_id", fileId)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
      .withErrorResponse[AppError](501)
        /**
   * Gets a file&#39;s info. ##### Permissions Must have &#x60;read_channel&#x60; permission or be uploader of the file. 
   * 
   * Expected answers:
   *   code 200 : FileInfo (The stored metadata for the given file)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param fileId The ID of the file info to get
   */
  def filesFileIdInfoGet(fileId: String): ApiRequest[FileInfo] =
    ApiRequest[FileInfo](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/files/{file_id}/info", "application/json")
      .withPathParam("file_id", fileId)
      .withSuccessResponse[FileInfo](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
      .withErrorResponse[AppError](501)
        /**
   * Gets a public link for a file that can be accessed without logging into Mattermost.
   * 
   * Expected answers:
   *   code 200 : String (A publicly accessible link to the given file)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param fileId The ID of the file to get a link for
   */
  def filesFileIdLinkGet(fileId: String): ApiRequest[String] =
    ApiRequest[String](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/files/{file_id}/link", "application/json")
      .withPathParam("file_id", fileId)
      .withSuccessResponse[String](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
      .withErrorResponse[AppError](501)
        /**
   * Gets a file&#39;s preview. ##### Permissions Must have &#x60;read_channel&#x60; permission or be uploader of the file. 
   * 
   * Expected answers:
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param fileId The ID of the file to get
   */
  def filesFileIdPreviewGet(fileId: String): ApiRequest[Unit] =
    ApiRequest[Unit](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/files/{file_id}/preview", "application/json")
      .withPathParam("file_id", fileId)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
      .withErrorResponse[AppError](501)
        /**
   * Gets a file&#39;s thumbnail. ##### Permissions Must have &#x60;read_channel&#x60; permission or be uploader of the file. 
   * 
   * Expected answers:
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param fileId The ID of the file to get
   */
  def filesFileIdThumbnailGet(fileId: String): ApiRequest[Unit] =
    ApiRequest[Unit](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/files/{file_id}/thumbnail", "application/json")
      .withPathParam("file_id", fileId)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
      .withErrorResponse[AppError](501)
        /**
   * Uploads a file that can later be attached to a post. ##### Permissions Must have &#x60;upload_file&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : InlineResponse2006 (Corresponding lists of the provided client_ids and the metadata that has been stored in the database for each one)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 413 : AppError (Content too large)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param files A file to be uploaded
   * @param channelId The ID of the channel that this file will be uploaded to
   * @param clientIds A unique identifier for the file that will be returned in the response
   */
  def filesPost(files: File, channelId: String, clientIds: Option[String] = None): ApiRequest[InlineResponse2006] =
    ApiRequest[InlineResponse2006](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/files", "multipart/form-data")
      .withFormParam("files", files)
      .withFormParam("channel_id", channelId)
      .withFormParam("client_ids", clientIds)
      .withSuccessResponse[InlineResponse2006](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](413)
      .withErrorResponse[AppError](501)
      

}
