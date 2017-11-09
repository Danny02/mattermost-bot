package mattermost.bot

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.util.ByteString
import io.circe.{Encoder, Printer}
import io.circe.syntax._

object CirceSupport {

  implicit def circeMarshaler[T: Encoder]: ToEntityMarshaller[T] = {
    val printer = Printer(
      preserveOrder = false,
      dropNullValues = true,
      indent = ""
    )

    def compactJson(t: T) = ByteString(printer.prettyByteBuffer(t.asJson))
    Marshaller.byteStringMarshaller(`application/json`).compose(compactJson)
  }

}
