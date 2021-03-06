/**
 * NOTE: This class is auto generated by the akka-scala (beta) swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen
 * For any issue or feedback, please open a ticket via https://github.com/swagger-api/swagger-codegen/issues/new
 */

package mattermost.api.client.model

import mattermost.api.client.core.ApiModel

case class ConfigSamlSettings (
  enable: Option[Boolean],
  verify: Option[Boolean],
  encrypt: Option[Boolean],
  idpUrl: Option[String],
  idpDescriptorUrl: Option[String],
  assertionConsumerServiceURL: Option[String],
  idpCertificateFile: Option[String],
  publicCertificateFile: Option[String],
  privateKeyFile: Option[String],
  firstNameAttribute: Option[String],
  lastNameAttribute: Option[String],
  emailAttribute: Option[String],
  usernameAttribute: Option[String],
  nicknameAttribute: Option[String],
  localeAttribute: Option[String],
  positionAttribute: Option[String],
  loginButtonText: Option[String]
) extends ApiModel


