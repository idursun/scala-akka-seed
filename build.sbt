name := "scala-akka-seed"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Akka Snapshots" at "http://repo.akka.io/snapshots"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT" withSources()
)
