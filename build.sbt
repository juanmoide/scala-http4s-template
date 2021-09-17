name := "Graph-server"

version := "0.1"

val http4sVersion = "0.23.3"
val pureConfigVersion = "0.16.0"
val logbackVersion = "1.2.5"

scalaVersion := "2.13.6"

idePackagePrefix := Some("dev.juanmoi.testserver")

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "com.github.pureconfig" %% "pureconfig" % pureConfigVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion % Runtime
)