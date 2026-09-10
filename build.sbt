lazy val root = (project in file("."))
  .settings(
    name := "sdec-internal-frontend-alpha-test",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.3.4",
    libraryDependencies ++= Dependencies.test,
    (Compile / compile) :=
      (Compile / compile)
        .dependsOn(
          Compile / scalafmtSbtCheck,
          Compile / scalafmtCheckAll
        )
        .value,
    Test / fork := true,
    Test / javaOptions ++= Seq(
      s"-Dbrowser=${sys.props.getOrElse("browser", "chrome")}",
      s"-Denvironment=${sys.props.getOrElse("environment", "local")}",
      s"-Dbrowser.option.headless=${sys.props.getOrElse("browser.option.headless", "true")}",
      s"-Dbrowser.usePreviousVersion=${sys.props.getOrElse("browser.usePreviousVersion", "true")}"
    ),
    Test / parallelExecution := false
  )
