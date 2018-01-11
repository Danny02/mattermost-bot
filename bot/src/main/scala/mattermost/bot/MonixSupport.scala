package mattermost.bot

import akka.NotUsed
import akka.http.scaladsl.marshalling.Marshaller
import akka.stream.scaladsl.Source
import monix.eval.Task
import monix.execution.Scheduler
import monix.reactive.Observable

import scala.concurrent.Future

/**
  * @author Daniel Heinrich
  * @since 14.11.2017
  */
object MonixSupport {

  implicit def taskMarshaller[A, B](implicit m: Marshaller[Future[A], B]): Marshaller[Task[A], B] =
    Marshaller(implicit ec ⇒ a => m(a.runAsync(Scheduler(ec))))

  implicit def observableMarshaller[A, B](implicit m: Marshaller[Source[A, NotUsed], B]): Marshaller[Observable[A], B] =
    Marshaller{implicit ec ⇒  obs =>
      val s = Scheduler(ec)
      m(Source.fromPublisher(obs.toReactivePublisher(s)))
    }

  implicit class TaskOps[E](t: Task[E]) {
    def background: Task[Unit] = Task.chooseFirstOf(Task.unit, t).map(_ => ())
  }
}
