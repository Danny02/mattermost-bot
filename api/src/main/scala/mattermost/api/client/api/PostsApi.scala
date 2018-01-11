/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import mattermost.api.client.core._
import mattermost.api.client.model._

object PostsApi {

  /**
   * Get a page of posts in a channel. Use the query parameters to modify the behaviour of this endpoint. The parameters &#x60;since&#x60;, &#x60;before&#x60; and &#x60;after&#x60; must not be used together. ##### Permissions Must have &#x60;read_channel&#x60; permission for the channel. 
   * 
   * Expected answers:
   *   code 200 : PostList (Post list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param channelId The channel ID to get the posts for
   * @param page The page to select
   * @param perPage The number of posts per page
   * @param since Provide a non-zero value in Unix time milliseconds to select posts created after that time
   * @param before A post id to select the posts that came before this one
   * @param after A post id to select the posts that came after this one
   */
  def channelsChannelIdPostsGet(channelId: String, page: Option[String], perPage: Option[String], since: Option[Int] = None, before: Option[String] = None, after: Option[String] = None): ApiRequest[PostList] =
    ApiRequest[PostList](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/channels/{channel_id}/posts", "application/json")
      .withQueryParam("page", page)
      .withQueryParam("per_page", perPage)
      .withQueryParam("since", since)
      .withQueryParam("before", before)
      .withQueryParam("after", after)
      .withPathParam("channel_id", channelId)
      .withSuccessResponse[PostList](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Create a new post in a channel. To create the post as a comment on another post, provide &#x60;root_id&#x60;. ##### Permissions Must have &#x60;create_post&#x60; permission for the channel the post is being created in. 
   * 
   * Expected answers:
   *   code 201 : Post (Post creation successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param post Post object to create
   */
  def postsPost(post: Post): ApiRequest[Post] =
    ApiRequest[Post](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/posts", "application/json")
      .withBody(post)
      .withSuccessResponse[Post](201)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Perform a post action, which allows users to interact with integrations through posts. ##### Permissions Must be authenticated and have the &#x60;read_channel&#x60; permission to the channel the post is in. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Post action successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId Post GUID
   * @param actionId Action GUID
   */
  def postsPostIdActionsActionIdPost(postId: String, actionId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/actions/{action_id}", "application/json")
      .withPathParam("post_id", postId)
      .withPathParam("action_id", actionId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Soft deletes a post, by marking the post as deleted in the database. Soft deleted posts will not be returned in post queries. ##### Permissions Must be logged in as the user or have &#x60;delete_others_posts&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Post deletion successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId ID of the post to delete
   */
  def postsPostIdDelete(postId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Gets a list of file information objects for the files attached to a post. ##### Permissions Must have &#x60;read_channel&#x60; permission for the channel the post is in. 
   * 
   * Expected answers:
   *   code 200 : Seq[FileInfo] (File info retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId ID of the post
   */
  def postsPostIdFilesInfoGet(postId: String): ApiRequest[Seq[FileInfo]] =
    ApiRequest[Seq[FileInfo]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/files/info", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[Seq[FileInfo]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Get a single post. ##### Permissions Must have &#x60;read_channel&#x60; permission for the channel the post is in or if the channel is public, have the &#x60;read_public_channels&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : Post (Post retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId ID of the post to get
   */
  def postsPostIdGet(postId: String): ApiRequest[Post] =
    ApiRequest[Post](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[Post](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Partially update a post by providing only the fields you want to update. Omitted fields will not be updated. The fields that can be updated are defined in the request body, all other provided fields will be ignored. ##### Permissions Must have the &#x60;edit_post&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Post (Post patch successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId Post GUID
   * @param body Post object that is to be updated
   */
  def postsPostIdPatchPut(postId: String, body: Body28): ApiRequest[Post] =
    ApiRequest[Post](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/patch", "application/json")
      .withBody(body)
      .withPathParam("post_id", postId)
      .withSuccessResponse[Post](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Pin a post to a channel it is in based from the provided post id string. ##### Permissions Must be authenticated and have the &#x60;read_channel&#x60; permission to the channel the post is in. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Pinned post successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId Post GUID
   */
  def postsPostIdPinPost(postId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/pin", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Update a post. Only the fields listed below are updatable, omitted fields will be treated as blank. ##### Permissions Must have &#x60;edit_post&#x60; permission for the channel the post is in. 
   * 
   * Expected answers:
   *   code 200 : Post (Post update successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId ID of the post to update
   * @param body Post object that is to be updated
   */
  def postsPostIdPut(postId: String, body: Body27): ApiRequest[Post] =
    ApiRequest[Post](ApiMethods.PUT, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}", "application/json")
      .withBody(body)
      .withPathParam("post_id", postId)
      .withSuccessResponse[Post](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Get a post and the rest of the posts in the same thread. ##### Permissions Must have &#x60;read_channel&#x60; permission for the channel the post is in or if the channel is public, have the &#x60;read_public_channels&#x60; permission for the team. 
   * 
   * Expected answers:
   *   code 200 : PostList (Post list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId ID of a post in the thread
   */
  def postsPostIdThreadGet(postId: String): ApiRequest[PostList] =
    ApiRequest[PostList](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/thread", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[PostList](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Unpin a post to a channel it is in based from the provided post id string. ##### Permissions Must be authenticated and have the &#x60;read_channel&#x60; permission to the channel the post is in. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (Unpinned post successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param postId Post GUID
   */
  def postsPostIdUnpinPost(postId: String): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/posts/{post_id}/unpin", "application/json")
      .withPathParam("post_id", postId)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Search posts in the team and from the provided terms string. ##### Permissions Must be authenticated and have the &#x60;view_team&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : PostList (Post list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param teamId Team GUID
   * @param body The search terms and logic to use in the search.
   */
  def teamsTeamIdPostsSearchPost(teamId: String, body: Body29): ApiRequest[PostList] =
    ApiRequest[PostList](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/teams/{team_id}/posts/search", "application/json")
      .withBody(body)
      .withPathParam("team_id", teamId)
      .withSuccessResponse[PostList](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
        /**
   * Get a page of flagged posts of a user provided user id string. Selects from a channel, team or all flagged posts by a user. ##### Permissions Must be user or have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : Seq[PostList] (Post list retrieval successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   * 
   * @param userId ID of the user
   * @param teamId Team ID
   * @param channelId Channel ID
   * @param page The page to select
   * @param perPage The number of posts per page
   */
  def usersUserIdPostsFlaggedGet(userId: String, teamId: Option[String] = None, channelId: Option[String] = None, page: Option[String], perPage: Option[String]): ApiRequest[Seq[PostList]] =
    ApiRequest[Seq[PostList]](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/users/{user_id}/posts/flagged", "application/json")
      .withQueryParam("team_id", teamId)
      .withQueryParam("channel_id", channelId)
      .withQueryParam("page", page)
      .withQueryParam("per_page", perPage)
      .withPathParam("user_id", userId)
      .withSuccessResponse[Seq[PostList]](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      

}
