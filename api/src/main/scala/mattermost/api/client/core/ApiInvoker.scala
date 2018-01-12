package mattermost.api.client.core

import java.io.File
import java.time.Instant

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.Uri.{Path, Query}
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{Authorization, GenericHttpCredentials}
import akka.http.scaladsl.unmarshalling.{Unmarshal, Unmarshaller}
import akka.stream.Materializer
import monix.eval.Task

import scala.util.Try

/**
  * @author Daniel Heinrich
  * @since 11.01.2018
  */
case class ApiInvoker(mmUri: String, token: String) {
  private val apiPath = "/api/v4"
  private val accessSchema = "Bearer"
  private val rateRemainingHeader = "X-Ratelimit-Remaining"
  private val rateResetHeader = "X-Ratelimit-Reset"
  private val speculativeDefaultRate = 50

  private[this] val rateLimiter = TaskRateLimiter()

  def request[R](apiRequest: ApiRequest[R])
                (implicit sys: ActorSystem, mat: Materializer, un: Unmarshaller[HttpEntity, R]): Task[R] = {

    require(apiRequest.bodyParam.isEmpty, "request with body are not supported!")
    require(apiRequest.formParams.values.forall(!_.isInstanceOf[File]), "file upload in form data is not supported!")

    val params = apiRequest.queryParams.mapValues {
      case s: String => s
      case o: Option[String] => o.getOrElse(Query.EmptyValue)
    }

    val path = apiPath + apiRequest.pathParams.foldLeft(apiRequest.operationPath) {
      case (p, (k, v: String)) => p.replace('{' + k + '}', v)
    }

    val reqUri = Uri(mmUri)
      .withPath(Path(path))
      .withQuery(Query(params))

    val auth: HttpHeader = Authorization.apply(GenericHttpCredentials(accessSchema, token))

    val formData = FormData(apiRequest.formParams.flatMap {
      case (_, None) => None
      case (k, Some(v)) => Some((k, v))
    }.mapValues {
      case i: Int => i.toString
      case s: String => s
    })

    val req = {
      val r = HttpRequest(uri = reqUri)
        .withHeaders(auth)
        .withMethod(mapMethod(apiRequest.method))

      if (apiRequest.formParams.isEmpty) r else r.withEntity(formData.toEntity)
    }

    val reqT = Task.deferFuture(Http().singleRequest(req))

    val ansT: Task[(RateLimit, R)] = reqT.flatMap { r =>
      val headerMap = r.headers.map(h => h.name() -> h.value()).toMap

      val rateO = for {
        remainingS <- headerMap.get(rateRemainingHeader)
        remainingI <- Try(remainingS.toInt).toOption
        resetS <- headerMap.get(rateResetHeader)
        resetI <- Try(resetS.toInt).toOption
      } yield RateLimit(remainingI, Instant.now().plusSeconds(resetI))

      val rate = rateO.getOrElse(RateLimit(limit = speculativeDefaultRate, Instant.now().plusSeconds(1)))

      val parsed = Task.fromFuture(Unmarshal(r.entity: HttpEntity).to[R])
      parsed.map(p => (rate, p))
    }

    rateLimiter.greenLight(ansT)
  }

  def mapMethod(m: ApiMethod): HttpMethod = m match {
    case a if a == ApiMethods.GET => HttpMethods.GET
    case a if a == ApiMethods.POST => HttpMethods.POST
    case a if a == ApiMethods.DELETE => HttpMethods.DELETE
    case a if a == ApiMethods.PUT => HttpMethods.PUT
  }
}