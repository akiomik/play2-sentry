package com.m3.play2.sentry

import play.api.mvc.{Filter, RequestHeader, Result}

import akka.stream.Materializer
import javax.inject.Inject
import scala.concurrent.Future

class SentryLoggingFilter @Inject()(implicit val mat: Materializer) extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

    SentryDispatcher.httpInterfaceValue.value = Option(PlayHttpInterface(requestHeader))
    nextFilter(requestHeader)
  }

}
