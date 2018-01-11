/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import mattermost.api.client.core._
import mattermost.api.client.model.{AppError, DataRetentionPolicy}

object DataretentionApi {

  /**
   * Gets the current data retention policy details from the server, including what data should be purged and the cutoff times for each data type that should be purged. __Minimum server version__: 4.3 ##### Permissions Requires an active session but no other permissions. 
   * 
   * Expected answers:
   *   code 200 : DataRetentionPolicy (Data retention policy details retrieved successfully.)
   *   code 500 : AppError (Something went wrong with the server)
   *   code 501 : AppError (Feature is disabled)
   */
  def dataRetentionPolicyGet(): ApiRequest[DataRetentionPolicy] =
    ApiRequest[DataRetentionPolicy](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/data_retention/policy", "application/json")
      .withSuccessResponse[DataRetentionPolicy](200)
      .withErrorResponse[AppError](500)
      .withErrorResponse[AppError](501)
      

}
