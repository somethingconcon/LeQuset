name                  := "LeQuest"

organization          := "dd"

scalaVersion          := "2.11.8"

version               := "0.0.1-SNAPSHOT"

scalacOptions := Seq(
  "-deprecation",
  "-Xlint",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings",
  "-Ywarn-unused-import",
  "-Yno-adapted-args",
  "-language:higherKinds",
  "-language:existentials",
  "-encoding", "UTF-8" //,
  // "-Ypartial-unification"
)

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += sbtResolver.value

libraryDependencies ++= {

  Seq(
    "com.chuusai"   %% "shapeless"                   % "2.3.2",
    "org.scalaz"    %% "scalaz-core"                 % "7.3.0-M7",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.3.0"       % "test",
    "org.scalatest" %% "scalatest"                   % "3.0.0"       % "test",
    "com.lihaoyi"    % "ammonite-repl"               % "0.7.7"       % "test" cross CrossVersion.full,

  // testable clients for test testing :D

  // Relational
    "com.typesafe.slick" %% "slick"     % "3.1.0" % "test",
    "org.slf4j"           % "slf4j-nop" % "1.6.4" % "test", // No Op Logging

  // NoSQL
    // Cassandra
    "com.datastax.cassandra"  % "cassandra-driver-core"    % "3.1.0" % "test",
    "com.datastax.cassandra"  % "cassandra-driver-mapping" % "3.1.0" % "test",
    "com.datastax.cassandra"  % "cassandra-driver-extras"  % "3.1.0" % "test",

    "com.websudos"            % "phantom-dsl_2.11" % "1.29.6" % "test", // Api Wrapper for the above
    // Dynamo
    // suh.url
    "com.amazonaws"           % "aws-java-sdk-dynamodb"    % "1.10.43" % "test",
    // "jp.co.bizreach"          %% "aws-dynamodb-scala"      % "0.0.5"   % "test",

  // Key Value
    // Local cache / in memory database
      // BerkeleyDB
      // http://docs.oracle.com/cd/E17277_02/html/GettingStartedGuide/index.html
      "berkeleydb"       % "berkeleydb" % "1.5.1" % "test",
      // Neo4j

      // Leveldb
      // https://github.com/google/leveldb
      "org.iq80.leveldb" % "leveldb" % "0.9" % "test",

      // MapDb
      // https://github.com/jankotek/mapdb
      "org.mapdb"        % "mapdb" % "3.0.2" % "test",

      // EhCache

      // Guava
      "com.google.guava" % "guava" % "20.0" % "test",

      // this is getting wild! I'm high as fuck! hehe just re-read this. still high
    // Hosted
      // Redis
        "net.debasishg"    %% "redisclient" % "3.2" % "test",
        // Java Client redis.clients
        // https://github.com/xetorthio/jedis/
        "redis.clients"    % "jedis" % "2.9.0" % "test"
  )
}

initialCommands in (Test, console) := """ammonite.repl.Main().run()"""
