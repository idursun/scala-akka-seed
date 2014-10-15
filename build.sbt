name := "scraper"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "akka" at "http://repo.akka.io/snapshots"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT" withSources()
)