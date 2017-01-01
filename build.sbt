name                  := "LeQuest"

organization          := "dd"

scalaVersion          := "2.11.8"

version               := "1.2.0-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "geezeo" at "https://bob-the-builder.geezeo.com/archiva/repository/geezeo"

resolvers += sbtResolver.value

libraryDependencies ++= {

  Seq(
    "org.scalaz"    %% "scalaz-core"                 % "7.3.0-M7",           
    "org.scalamock" %% "scalamock-scalatest-support" % "3.3.0"       % "test",
    "org.scalatest" %% "scalatest"                   % "3.0.0"       % "test",
    "com.lihaoyi"    % "ammonite-repl"               % "0.7.7"       % "test" cross CrossVersion.full
  )
}

initialCommands in (Test, console) := """ammonite.repl.Main().run()"""
