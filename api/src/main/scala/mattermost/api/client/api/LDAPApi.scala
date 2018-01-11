/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import mattermost.api.client.core._
import mattermost.api.client.model.{AppError, StatusOK}

object LDAPApi {

  /**
   * Synchronize any user attribute changes in the configured AD/LDAP server with Mattermost. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (LDAP sync successful)
   *   code 501 : AppError (Feature is disabled)
   */
  def ldapSyncPost(): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/ldap/sync", "application/json")
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](501)
        /**
   * Test the current AD/LDAP configuration to see if the AD/LDAP server can be contacted successfully. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (LDAP test successful)
   *   code 500 : AppError (Something went wrong with the server)
   *   code 501 : AppError (Feature is disabled)
   */
  def ldapTestPost(): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/ldap/test", "application/json")
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](500)
      .withErrorResponse[AppError](501)
      

}
