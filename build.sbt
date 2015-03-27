organization in ThisBuild := "nl.proja"


name := """PiShake"""

description := """PiShake"""

version in ThisBuild := "0.1.0"

scalaVersion := "2.11.6"

scalaVersion in ThisBuild := scalaVersion.value

publishMavenStyle := true

resolvers in ThisBuild ++= Seq(
  "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Maven Repository" at "https://oss.sonatype.org/content/groups/public",
  Resolver.mavenLocal
)

libraryDependencies in ThisBuild ++= Seq(
)

dependencyOverrides in ThisBuild ++= Set(
  "org.scala-lang" % "scala-compiler" % scalaVersion.value,
  "org.scala-lang" % "scala-library" % scalaVersion.value
)

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-encoding", "UTF-8")

val pi4jVersion = "1.0-SNAPSHOT"
val akkaVersion = "2.3.9"
val scalaLoggingVersion = "3.1.0"
val slf4jVersion = "1.7.10"
val logbackVersion = "1.1.2"
val scalatestVersion = "2.2.4"
val configVersion = "1.2.1"

lazy val root = project.in(file(".")).settings(
  packagedArtifacts in file(".") := Map.empty,
  run := {
    (run in server in Compile).evaluated
  }
).aggregate(
    common, server
  )

lazy val common = project.settings(
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion
  )
)


lazy val server = project.settings(
  libraryDependencies ++= Seq(
    "com.pi4j" % "pi4j-core" % pi4jVersion withSources() withJavadoc(),
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-remote" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe" % "config" % configVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "org.slf4j" % "slf4j-api" % slf4jVersion,
    "ch.qos.logback" % "logback-classic" % logbackVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  )
).dependsOn(common)

mainClass in assembly := Some("nl.proja.pishake.PiShake")

