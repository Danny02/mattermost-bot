import sbt.Keys.scalacOptions

val circeVersion = "0.9.0-M2"
val akkaHttpVersion = "10.0.10"

lazy val commonSettings = Seq(
  organization := "de.arbeitsagentur.at",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val api = project.settings(
  commonSettings,
  name := "mattermost-api-v4",
  libraryDependencies ++= Seq(
    "com.typesafe" % "config" % "1.3.2",
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "org.json4s" %% "json4s-jackson" % "3.5.3"
  ),
  scalacOptions := Seq(
    "-unchecked",
    "-deprecation",
    "-feature"
  )
)

enablePlugins(JavaAppPackaging)
maintainer in Docker := "Daniel Heinrich"
dockerBaseImage := "openjdk:8-jre-slim"
dockerExposedPorts += 5000

lazy val bot = project.settings(
  commonSettings,
  name := "bot-server",
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "io.monix" %% "monix" % "2.3.0",
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-generic-extras" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "de.heikoseeberger" %% "akka-http-circe" % "1.19.0-M3",
    "org.apache.pdfbox" % "pdfbox" % "2.0.4"
  )
).dependsOn(api)
