ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.8"
libraryDependencies += "io.circe" %% "circe-core" % "0.14.1"
libraryDependencies += "io.circe" %% "circe-parser" % "0.14.1"
libraryDependencies += "io.circe" %% "circe-generic" % "0.14.1"
lazy val root = (project in file("."))
  .settings(
    name := "Homework1"
  )
