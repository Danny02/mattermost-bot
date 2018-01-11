/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import java.io.File

import mattermost.api.client.core._
import mattermost.api.client.model._

object TeamsApi {

  /**
   * For regular users only returns open teams. Users with the \&quot;manage_system\&quot; permission will return teams regardless of type. The result is based on query string parameters - page and per_page. ##### Permissions Must be authenticated. \&quot;manage_system\&quot; permission is required to show all teams.  
   * 
   * Expected answers:
   *   code 200 : Seq[Team] (Team list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   * 
   * @param page The page to select.
   * @param perPage The number of teams per page.
   */
  def teamsGet(page: Option[String], perPage: Option[String]): ApiRequest[Seq[Team]] =
    ApiRequest[Seq[Team]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams", "application/json")
      .withQueryParam("page", page)
      .withQueryParam("per_page", perPage)
      .withSuccessResponse[Seq[Team]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
        /**
   * Get the &#x60;name&#x60;, &#x60;display_name&#x60;, &#x60;description&#x60; and &#x60;id&#x60; for a team from the invite id.  __Minimum server version__: 4.0  ##### Permissions No authentication required. 
   * 
   * Expected answers:
   *   code 200 : InlineResponse2004 (Team invite info retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   * 
   * @param inviteId Invite id for a team
   */
  def teamsInviteInviteIdGet(inviteId: String): ApiRequest[InlineResponse2004] =
    ApiRequest[InlineResponse2004](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/invite/{invite_id}", "application/json")
      .withPathParam("invite_id", inviteId)
      .withSuccessResponse[InlineResponse2004](200)
      .withErrorResponse[AppError](400)
        /**
   * Using either an invite id or hash/data pair from an email invite link, add a user to a team. ##### Permissions Must be authenticated. 
   * 
   * Expected answers:
   *   code 201 : TeamMember (Team member creation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param hash Hash value with time, team id and and InviteSaltId
   * @param data Data with time and team id
   * @param inviteId Invite id to add user to the team
   */
  def teamsMembersInvitePost(hash: Option[String] = None, data: Option[String] = None, inviteId: Option[String] = None): ApiRequest[TeamMember] =
    ApiRequest[TeamMember](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/members/invite", "application/json")
      .withQueryParam("hash", hash)
      .withQueryParam("data", data)
      .withQueryParam("invite_id", inviteId)
      .withSuccessResponse[TeamMember](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Check if the team exists based on a team name. 
   * 
   * Expected answers:
   *   code 200 : TeamExists (Team retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 404 : AppError (Resource not found)
   * 
   * @param name Team Name
   */
  def teamsNameNameExistsGet(name: String): ApiRequest[TeamExists] =
    ApiRequest[TeamExists](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/name/{name}/exists", "application/json")
      .withPathParam("name", name)
      .withSuccessResponse[TeamExists](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](404)
        /**
   * Get a team based on provided name string ##### Permissions Must be authenticated, team type is open and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Team (Team retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param name Team Name
   */
  def teamsNameNameGet(name: String): ApiRequest[Team] =
    ApiRequest[Team](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/name/{name}", "application/json")
      .withPathParam("name", name)
      .withSuccessResponse[Team](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Create a new team on the system. ##### Permissions Must be authenticated and have the &#x60;create_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 201 : Team (Team creation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param body Team that is to be created
   */
  def teamsPost(body: Body16): ApiRequest[Team] =
    ApiRequest[Team](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams", "application/json")
      .withBody(body)
      .withSuccessResponse[Team](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Search teams based on search term provided in the request body. ##### Permissions Logged in user only shows open teams Logged in user with \&quot;manage_system\&quot; permission shows all teams 
   * 
   * Expected answers:
   *   code 200 : Seq[Team] (Teams search successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param body Search criteria
   */
  def teamsSearchPost(body: Body19): ApiRequest[Seq[Team]] =
    ApiRequest[Seq[Team]](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/search", "application/json")
      .withBody(body)
      .withSuccessResponse[Seq[Team]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Delete a team softly and put in archived only. ##### Permissions Must have the &#x60;manage_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Team deletion successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param permanent Permanently delete the team, to be used for compliance reasons only.
   */
  def teamsTeamIdDelete(teamId: String, permanent: Option[Boolean]): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}", "application/json")
      .withQueryParam("permanent", permanent)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a team on the system. ##### Permissions Must be authenticated and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Team (Team retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   */
  def teamsTeamIdGet(teamId: String): ApiRequest[Team] =
    ApiRequest[Team](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}", "application/json")
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Team](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Import a team into a existing team. Import users, channels, posts, hooks. ##### Permissions Must have &#x60;permission_import_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : InlineResponse2003 (JSON object containing a base64 encoded text file of the import logs in its &#x60;results&#x60; property.)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param file A file to be uploaded in zip format.
   * @param filesize The size of the zip file to be imported.
   * @param importFrom String that defines from which application the team was exported to be imported into Mattermost.
   * @param teamId Team GUID
   */
  def teamsTeamIdImportPost(file: File, filesize: Int, importFrom: String, teamId: String): ApiRequest[InlineResponse2003] =
    ApiRequest[InlineResponse2003](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/import", "multipart/form-data")
      .withFormParam("file", file)
      .withFormParam("filesize", filesize)
      .withFormParam("importFrom", importFrom)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[InlineResponse2003](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](403)
        /**
   * Invite users to the existing team usign the user&#39;s email. ##### Permissions Must have &#x60;invite_to_team&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Users invite successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param teamId Team GUID
   * @param body List of user&#39;s email
   */
  def teamsTeamIdInviteEmailPost(teamId: String, body: Seq[String]): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/invite/email", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Add a number of users to the team by user_id. ##### Permissions Must be authenticated. Authenticated user must have the &#x60;add_user_to_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 201 : Seq[TeamMember] (Team members created successfully.)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param body 
   */
  def teamsTeamIdMembersBatchPost(teamId: String, body: Seq[TeamMember]): ApiRequest[Seq[TeamMember]] =
    ApiRequest[Seq[TeamMember]](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members/batch", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Seq[TeamMember]](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a page team members list based on query string parameters - team id, page and per page. ##### Permissions Must be authenticated and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Seq[TeamMember] (Team members retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param page The page to select.
   * @param perPage The number of users per page.
   */
  def teamsTeamIdMembersGet(teamId: String, page: Option[String], perPage: Option[String]): ApiRequest[Seq[TeamMember]] =
    ApiRequest[Seq[TeamMember]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members", "application/json")
      .withQueryParam("page", page)
      .withQueryParam("per_page", perPage)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Seq[TeamMember]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a list of team members based on a provided array of user ids. ##### Permissions Must have &#x60;view_team&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : Seq[TeamMember] (Team members retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param teamId Team GUID
   * @param body List of user ids
   */
  def teamsTeamIdMembersIdsPost(teamId: String, body: Seq[String]): ApiRequest[Seq[TeamMember]] =
    ApiRequest[Seq[TeamMember]](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members/ids", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Seq[TeamMember]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Add user to the team by user_id. ##### Permissions Must be authenticated and team be open to add self. For adding another user, authenticated user must have the &#x60;add_user_to_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 201 : TeamMember (Team member creation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param body 
   */
  def teamsTeamIdMembersPost(teamId: String, body: TeamMember): ApiRequest[TeamMember] =
    ApiRequest[TeamMember](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[TeamMember](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Delete the team member object for a user, effectively removing them from a team. ##### Permissions Must be logged in as the user or have the &#x60;remove_user_from_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Team member deletion successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param userId User GUID
   */
  def teamsTeamIdMembersUserIdDelete(teamId: String, userId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members/{user_id}", "application/json")
      .withPathParam("team_id", teamId)
      .withPathParam("user_id", userId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a team member on the system. ##### Permissions Must be authenticated and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : TeamMember (Team member retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param userId User GUID
   */
  def teamsTeamIdMembersUserIdGet(teamId: String, userId: String): ApiRequest[TeamMember] =
    ApiRequest[TeamMember](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members/{user_id}", "application/json")
      .withPathParam("team_id", teamId)
      .withPathParam("user_id", userId)
      .withSuccessResponse[TeamMember](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Update a team member roles. Valid team roles are \&quot;team_user\&quot;, \&quot;team_admin\&quot; or both of them. Overwrites any previously assigned team roles. ##### Permissions Must be authenticated and have the &#x60;manage_team_roles&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Team member roles update successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param userId User GUID
   * @param body Space-delimited team roles to assign to the user
   */
  def teamsTeamIdMembersUserIdRolesPut(teamId: String, userId: String, body: Body20): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/members/{user_id}/roles", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withPathParam("user_id", userId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Partially update a team by providing only the fields you want to update. Omitted fields will not be updated. The fields that can be updated are defined in the request body, all other provided fields will be ignored. ##### Permissions Must have the &#x60;manage_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Team (team patch successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param teamId Team GUID
   * @param body Team object that is to be updated
   */
  def teamsTeamIdPatchPut(teamId: String, body: Body18): ApiRequest[Team] =
    ApiRequest[Team](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/patch", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Team](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Update a team by providing the team object. The fields that can be updated are defined in the request body, all other provided fields will be ignored. ##### Permissions Must have the &#x60;manage_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Team (Team update successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   * @param body Team to update
   */
  def teamsTeamIdPut(teamId: String, body: Body17): ApiRequest[Team] =
    ApiRequest[Team](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[Team](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a team stats on the system. ##### Permissions Must be authenticated and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : TeamStats (Team stats retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param teamId Team GUID
   */
  def teamsTeamIdStatsGet(teamId: String): ApiRequest[TeamStats] =
    ApiRequest[TeamStats](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/stats", "application/json")
      .withPathParam("team_id", teamId)
      .withSuccessResponse[TeamStats](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get a list of teams that a user is on. ##### Permissions Must be authenticated as the user or have the &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Seq[Team] (Team list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param userId User GUID
   */
  def usersUserIdTeamsGet(userId: String): ApiRequest[Seq[Team]] =
    ApiRequest[Seq[Team]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/teams", "application/json")
      .withPathParam("user_id", userId)
      .withSuccessResponse[Seq[Team]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Get a list of team members for a user. Useful for getting the ids of teams the user is on and the roles they have in those teams. ##### Permissions Must be logged in as the user or have the &#x60;edit_other_users&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Seq[TeamMember] (Team members retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param userId User GUID
   */
  def usersUserIdTeamsMembersGet(userId: String): ApiRequest[Seq[TeamMember]] =
    ApiRequest[Seq[TeamMember]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/teams/members", "application/json")
      .withPathParam("user_id", userId)
      .withSuccessResponse[Seq[TeamMember]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get the unread mention and message counts for a team for the specified user. ##### Permissions Must be the user or have &#x60;edit_other_users&#x60; permission and have &#x60;view_team&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : TeamUnread (Team unread count retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 404 : AppError (Resource not found)
   * 
   * @param userId User GUID
   * @param teamId Team GUID
   */
  def usersUserIdTeamsTeamIdUnreadGet(userId: String, teamId: String): ApiRequest[TeamUnread] =
    ApiRequest[TeamUnread](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/teams/{team_id}/unread", "application/json")
      .withPathParam("user_id", userId)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[TeamUnread](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](404)
        /**
   * Get the count for unread messages and mentions in the teams the user is a member of. ##### Permissions Must be logged in. 
   * 
   * Expected answers:
   *   code 200 : Seq[TeamUnread] (Team unreads retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param userId User GUID
   * @param excludeTeam Optional team id to be excluded from the results
   */
  def usersUserIdTeamsUnreadGet(userId: String, excludeTeam: String): ApiRequest[Seq[TeamUnread]] =
    ApiRequest[Seq[TeamUnread]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/teams/unread", "application/json")
      .withQueryParam("exclude_team", excludeTeam)
      .withPathParam("user_id", userId)
      .withSuccessResponse[Seq[TeamUnread]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      

}

