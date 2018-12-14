name := "Planning_Permission"

version := "0.1"

//scalaVersion := "2.12.8"
scalaVersion := "2.11.12"

resolvers += Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  "junit"             % "junit"           % "4.5",
  "org.scalatest"     %% "scalatest"      % "3.0.1",
  "org.apache.spark"  %% "spark-core"     % "2.3.0",
  "org.apache.spark"  %% "spark-sql"      % "2.4.0"
)
