name := """scala-play-sample"""
organization := "com.stobita"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.1"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.stobita.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.stobita.binders._"
