/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */
package mattermost.api.client.api

import java.io.File

import mattermost.api.client.core._
import mattermost.api.client.model.{AppError, SamlCertificateStatus, StatusOK}

object SAMLApi {

  /**
   * Delete the current IDP certificate being used with your SAML configuration. This will also disable SAML on your system as this certificate is required for SAML. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate delete successful)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   */
  def samlCertificateIdpDelete(): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/saml/certificate/idp", "application/json")
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Upload the IDP certificate to be used with your SAML configuration. This will also set the filename for the IdpCertificateFile setting in your &#x60;config.json&#x60;. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate upload successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param certificate The IDP certificate file
   */
  def samlCertificateIdpPost(certificate: File): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/saml/certificate/idp", "multipart/form-data")
      .withFormParam("certificate", certificate)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Delete the current private key being used with your SAML configuration. This will also disable encryption for SAML on your system as this key is required for that. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate delete successful)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   */
  def samlCertificatePrivateDelete(): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/saml/certificate/private", "application/json")
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Upload the private key to be used for encryption with your SAML configuration. This will also set the filename for the PrivateKeyFile setting in your &#x60;config.json&#x60;. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate upload successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param certificate The private key file
   */
  def samlCertificatePrivatePost(certificate: File): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/saml/certificate/private", "multipart/form-data")
      .withFormParam("certificate", certificate)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Delete the current public certificate being used with your SAML configuration. This will also disable encryption for SAML on your system as this certificate is required for that. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate delete successful)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   */
  def samlCertificatePublicDelete(): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.DELETE, "http://your-mattermost-url.com/api/v4", "/saml/certificate/public", "application/json")
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Upload the public certificate to be used for encryption with your SAML configuration. This will also set the filename for the PublicCertificateFile setting in your &#x60;config.json&#x60;. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : StatusOK (SAML certificate upload successful)
   *   code 400 : AppError (Invalid or missing parameters in URL or request body)
   *   code 401 : AppError (No access token provided)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   * 
   * @param certificate The public certificate file
   */
  def samlCertificatePublicPost(certificate: File): ApiRequest[StatusOK] =
    ApiRequest[StatusOK](ApiMethods.POST, "http://your-mattermost-url.com/api/v4", "/saml/certificate/public", "multipart/form-data")
      .withFormParam("certificate", certificate)
      .withSuccessResponse[StatusOK](200)
      .withErrorResponse[AppError](400)
      .withErrorResponse[AppError](401)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Get the status of the uploaded certificates and keys in use by your SAML configuration. ##### Permissions Must have &#x60;manage_system&#x60; permission. 
   * 
   * Expected answers:
   *   code 200 : SamlCertificateStatus (SAML certificate status retrieval successful)
   *   code 403 : AppError (Do not have appropriate permissions)
   *   code 501 : AppError (Feature is disabled)
   */
  def samlCertificateStatusGet(): ApiRequest[SamlCertificateStatus] =
    ApiRequest[SamlCertificateStatus](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/saml/certificate/status", "application/json")
      .withSuccessResponse[SamlCertificateStatus](200)
      .withErrorResponse[AppError](403)
      .withErrorResponse[AppError](501)
        /**
   * Get SAML metadata from the server. SAML must be configured properly. ##### Permissions No permission required. 
   * 
   * Expected answers:
   *   code 200 : String (SAML metadata retrieval successful)
   *   code 501 : AppError (Feature is disabled)
   */
  def samlMetadataGet(): ApiRequest[String] =
    ApiRequest[String](ApiMethods.GET, "http://your-mattermost-url.com/api/v4", "/saml/metadata", "application/json")
      .withSuccessResponse[String](200)
      .withErrorResponse[AppError](501)
      

}

