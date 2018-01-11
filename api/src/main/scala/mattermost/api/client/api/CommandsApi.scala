/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import mattermost.api.client.core._
import mattermost.api.client.model._

object CommandsApi {

  /**
   * Delete a command based on command id string. ##### Permissions Must have &#x60;manage_slash_commands&#x60; permission for the team the command is in. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Command deletion successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param commandId ID of the command to delete
   */
  def commandsCommandIdDelete(commandId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/commands/{command_id}", "application/json")
      .withPathParam("command_id", commandId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Update a single command based on command id string and Command struct. ##### Permissions Must have &#x60;manage_slash_commands&#x60; permission for the team the command is in. 
   * 
   * Expected answers:
   *   code 200 : Command (Command updated successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param commandId ID of the command to update
   * @param body 
   */
  def commandsCommandIdPut(commandId: String, body: Command): ApiRequest[Command] =
    ApiRequest[Command](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/commands/{command_id}", "application/json")
      .withBody(body)
      .withPathParam("command_id", commandId)
      .withSuccessResponse[Command](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Generate a new token for the command based on command id string. ##### Permissions Must have &#x60;manage_slash_commands&#x60; permission for the team the command is in. 
   * 
   * Expected answers:
   *   code 200 : InlineResponse2008 (Token generation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param commandId ID of the command to generate the new token
   */
  def commandsCommandIdRegenTokenPut(commandId: String): ApiRequest[InlineResponse2008] =
    ApiRequest[InlineResponse2008](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/commands/{command_id}/regen_token", "application/json")
      .withPathParam("command_id", commandId)
      .withSuccessResponse[InlineResponse2008](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Execute a command on a team. ##### Permissions Must have &#x60;use_slash_commands&#x60; permission for the team the command is in. 
   * 
   * Expected answers:
   *   code 200 : CommandResponse (Command execution successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param body command to be executed
   */
  def commandsExecutePost(body: Body37): ApiRequest[CommandResponse] =
    ApiRequest[CommandResponse](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/commands/execute", "application/json")
      .withBody(body)
      .withSuccessResponse[CommandResponse](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * List commands for a team. ##### Permissions &#x60;manage_slash_commands&#x60; if need list custom commands. 
   * 
   * Expected answers:
   *   code 200 : Seq[Command] (List Commands retrieve successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param teamId The team id.
   * @param customOnly To get only the custom commands. If set to false will get the custom if the user have access plus the system commands, otherwise just the system commands. 
   */
  def commandsGet(teamId: Option[String] = None, customOnly: Option[String]): ApiRequest[Seq[Command]] =
    ApiRequest[Seq[Command]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/commands", "application/json")
      .withQueryParam("team_id", teamId)
      .withQueryParam("custom_only", customOnly)
      .withSuccessResponse[Seq[Command]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Create a command for a team. ##### Permissions &#x60;manage_slash_commands&#x60; for the team the command is in. 
   * 
   * Expected answers:
   *   code 201 : Command (Command creation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param body command to be created
   */
  def commandsPost(body: Body36): ApiRequest[Command] =
    ApiRequest[Command](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/commands", "application/json")
      .withBody(body)
      .withSuccessResponse[Command](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * List autocomplete commands in the team. ##### Permissions &#x60;view_team&#x60; for the team. 
   * 
   * Expected answers:
   *   code 200 : Seq[Command] (Autocomplete commands retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param teamId Team GUID
   */
  def teamsTeamIdCommandsAutocompleteGet(teamId: String): ApiRequest[Seq[Command]] =
    ApiRequest[Seq[Command]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/commands/autocomplete", "application/json")
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Seq[Command]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      

}

