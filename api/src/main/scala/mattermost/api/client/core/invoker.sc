import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.Uri.{Path, Query}
import akka.http.scaladsl.model.headers.{Authorization, GenericHttpCredentials}
import akka.http.scaladsl.model.{HttpHeader, HttpRequest, ResponseEntity, Uri}
import akka.http.scaladsl.unmarshalling.{Unmarshal, Unmarshaller}
import akka.stream.{ActorMaterializer, Materializer}
import mattermost.api.client.api.UsersApi
import mattermost.api.client.core.ApiRequest
import mattermost.api.client.model.{User, UserNotifyProps}
import spray.json._

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

case class Invoker(mmUri: String, token: String)(implicit sys: ActorSystem) {

  def request[R](apiRequest: ApiRequest[R])
                (implicit sys: ActorSystem, mat: Materializer, un: Unmarshaller[ResponseEntity, R]): Future[R] = {

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
    Http().singleRequest(req).flatMap(r => Unmarshal(r.entity).to[R])
  }
}

object JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object AnyFormat extends RootJsonFormat[Any] {
    override def write(obj: Any) = JsNull
    override def read(json: JsValue) = null
  }
  implicit val userNotifyFormat = jsonFormat7(UserNotifyProps)
  implicit val userFormat = jsonFormat19(User)
}

def doStuff() = {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val invoker = Invoker("http://l3047022:8888", "9o74sce753rp7cyj1x87dw6xdh")

  import JsonSupport._
  val userF = invoker.request(UsersApi.usersEmailEmailGet("daniel.heinrich2@arbeitsagentur.de"))

  val user = Await.result(userF, 5 seconds)
  println(user)
}

doStuff()