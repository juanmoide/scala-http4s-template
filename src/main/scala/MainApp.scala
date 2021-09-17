package dev.juanmoi.testserver

import cats.effect.{ExitCode, IO, IOApp}

object MainApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    new Server[IO].serve.compile.lastOrError
  }
}
