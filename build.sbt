name := "mattermost-bot"

val circeVersion = "0.9.0-M2"
val akkaHttpVersion = "10.0.10"

lazy val commonSettings = Seq(
  organization := "de.arbeitsagentur.at",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.12.4"
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

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
