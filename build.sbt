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
    "com.typesafe.akka" %% "akka-actor" % "2.3.9"
  )
)

lazy val server = project.settings(
  libraryDependencies ++= Seq(
    "com.pi4j" % "pi4j-core" % "1.0-SNAPSHOT" withSources() withJavadoc(),
    "com.typesafe.akka" %% "akka-actor" % "2.3.9",
    "com.typesafe.akka" %% "akka-remote" % "2.3.9",
    "com.typesafe.akka" %% "akka-slf4j" % "2.3.9",
    "com.typesafe" % "config" % "1.2.1",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
    "org.slf4j" % "slf4j-api" % "1.7.10",
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  )
).dependsOn(common)

mainClass in assembly := Some("nl.proja.pishake.PiShake")

