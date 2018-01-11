/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import mattermost.api.client.core._
import mattermost.api.client.model.{AppError, Body15, Status}

object StatusApi {

  /**
   * Get a list of user statuses by id from the server. ##### Permissions Must be authenticated. 
   * 
   * Expected answers:
   *   code 200 : Seq[Status] (User statuses retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   */
  def usersStatusIdsGet(): ApiRequest[Seq[Status]] =
    ApiRequest[Seq[Status]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/status/ids", "application/json")
      .withSuccessResponse[Seq[Status]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
        /**
   * Get user status by id from the server. ##### Permissions Must be authenticated. 
   * 
   * Expected answers:
   *   code 200 : Status (User status retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   * 
   * @param userId User ID
   */
  def usersUserIdStatusGet(userId: String): ApiRequest[Status] =
    ApiRequest[Status](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/status", "application/json")
      .withPathParam("user_id", userId)
      .withSuccessResponse[Status](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
        /**
   * Manually set a user&#39;s status. When setting a user&#39;s status, the status will remain that value until set \&quot;online\&quot; again, which will return the status to being automatically updated based on user activity. ##### Permissions Must have &#x60;edit_other_users&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : Status (User status update successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   * 
   * @param userId User ID
   * @param body Status object that is to be updated
   */
  def usersUserIdStatusPut(userId: String, body: Body15): ApiRequest[Status] =
    ApiRequest[Status](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/status", "application/json")
      .withBody(body)
      .withPathParam("user_id", userId)
      .withSuccessResponse[Status](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      

}
