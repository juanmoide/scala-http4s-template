package dev.juanmoi.testserver

import cats.effect.{IO, Sync}
import org.http4s.{HttpRoutes, Response}
import org.http4s.dsl.Http4sDsl
import cats.syntax.all._
import org.http4s._
import org.http4s.implicits._

// Routes class is waiting to F to be a Sync[F] (implicit) and extends from Http4sDsl that expect F too
class Routes[F[_]:Sync] extends Http4sDsl[F]{
  // Defining endpoints
  def endpoints: HttpRoutes[F] = HttpRoutes.of[F] {
    // Method: GET -> route: host:port/hello -> localhost:8080/hello
    case GET -> Root / "hello" =>
      Sync[F].delay(Response(Status.Ok).withEntity("Hello world!"))
      // response: "Hello world!"
    // Method: GET -> route: host:port/hello -> localhost:8080/hello/Juanma
    case GET -> Root / "hello" / name =>
      Sync[F].delay(Response(Status.Ok).withEntity(s"Hello $name!"))
      // response" "Hello Juanma!"
  }
}
