package mattermost.api.client.core

import java.time.{Duration, Instant}

import monix.eval.{MVar, Task}

import scala.concurrent.duration.{Duration => SDuration}


case class RateLimit(limit: Int = 0, nextEpoche: Instant = Instant.MIN) {
  def use = {
    println(s"limit is now $limit, ${Instant.now()}")
    RateLimit(limit - 1, nextEpoche)
  }
}

class TaskRateLimiter private() {
  private[this] val limit = MVar(RateLimit())

  def greenLight[E](task: Task[(RateLimit, E)]): Task[E] = {
    limit.take.flatMap { l =>
      if (l.limit == 0) {
        val now = Instant.now()
        val b = if (l.nextEpoche.isBefore(now)) {
          task
        } else {
          val delay = Duration.between(now, l.nextEpoche)
          task.delayExecution(SDuration.fromNanos(delay.toNanos))
        }

        for {
          t <- b
          _ <- limit.put(t._1)
        } yield t._2
      } else {
        for {
          _ <- limit.put(l.use)
          c <- task
        } yield c._2
      }
    }
  }
}

object TaskRateLimiter {
  def apply(): TaskRateLimiter = new TaskRateLimiter()
}
