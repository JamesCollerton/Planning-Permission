name := "Planning_Permission"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "junit"          % "junit"           % "4.5",
  "org.scalatest"  %% "scalatest"      % "3.0.1"
)