package mattermost.api.client.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.Uri.{Path, Query}
import akka.http.scaladsl.model.headers.{Authorization, GenericHttpCredentials}
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.{Unmarshal, Unmarshaller}
import akka.stream.Materializer

import scala.concurrent.Future

/**
  * @author Daniel Heinrich
  * @since 11.01.2018
  */
case class ApiInvoker(mmUri: String, token: String) {

  def request[R](apiRequest: ApiRequest[R])
                (implicit sys: ActorSystem, mat: Materializer, un: Unmarshaller[HttpEntity, R]): Future[R] = {

    val params = apiRequest.queryParams.mapValues {
      case s: String => s
      case o: Option[String] => o.getOrElse(Query.EmptyValue)
    }

    val path = "/api/v4" + apiRequest.pathParams.foldLeft(apiRequest.operationPath) {
      case (p, (k, v: String)) => p.replace('{' + k + '}', v)
    }

    val reqUri = Uri(mmUri)
      .withPath(Path(path))
      .withQuery(Query(params))

    val auth: HttpHeader = Authorization.apply(GenericHttpCredentials("Bearer", token))

    val req = HttpRequest(uri = reqUri).withHeaders(auth)
    implicit val ec = mat.executionContext
    Http().singleRequest(req).flatMap(r => Unmarshal(r.entity: HttpEntity).to[R])
  }
}