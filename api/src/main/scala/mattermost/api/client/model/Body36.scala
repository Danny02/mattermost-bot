/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class Body36 (
  /* Team ID to where the command should be created */
  teamId: String,
  /* `'P'` for post request, `'G'` for get request */
  method: String,
  /* Activation word to trigger the command */
  trigger: String,
  /* The URL that the command will make the request */
  url: String
) extends ApiModel


