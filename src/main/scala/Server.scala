package dev.juanmoi.testserver

import cats.effect.{Async, ExitCode, Sync}
import cats.implicits._
import org.http4s.blaze.server.BlazeServerBuilder
import pureconfig.ConfigSource
import pureconfig.generic.auto._
import fs2.Stream

import scala.concurrent.ExecutionContext

class Server[F[_]: Async] {
  // for comprehension
  // helper to inner flatMaps (<-) and maps (=)
  def serve =
    for {
      // <- = flatMap
      // Stream.eval(...) = arguments
      // Inside Stream.eval = code

      // Load config using pureConfig
      // It modifies what is inside F using pure functions like delay, so we use flatMap
      config <- Stream.eval(Async[F].delay(ConfigSource.default.loadOrThrow[Config]))

      // Load the routes and responses
      // It uses F to load the routes, so we use map instead
      routes = new Routes[F].endpoints

      // Prepare the service using previous configuration
      service <- BlazeServerBuilder[F](ExecutionContext.global)
        .bindHttp(config.port)
        .withHttpApp(routes.orNotFound)
        .serve
    } yield service
}
