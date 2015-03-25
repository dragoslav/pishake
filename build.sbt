organization := "nl.proja"


name := """PiShake"""

version := "0.1.0"

scalaVersion := "2.11.6"

description := """PiShake"""


resolvers ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  Resolver.mavenLocal
)

val akkaVersion = "2.3.9"
val scalaLoggingVersion = "3.1.0"
val slf4jVersion = "1.7.10"
val logbackVersion = "1.1.2"
val junitVersion = "4.11"
val scalatestVersion = "2.2.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
  "org.slf4j" % "slf4j-api" % slf4jVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "junit" % "junit" % "4.11" % "test"
)


