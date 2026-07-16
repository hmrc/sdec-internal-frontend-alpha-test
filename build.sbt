lazy val root = (project in file("."))
  .settings(
    name := "sdec-internal-frontend-alpha-test",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := "3.3.4",
    libraryDependencies ++= Dependencies.test,
    (Compile / compile) := ((Compile / compile) dependsOn (Compile / scalafmtSbtCheck, Compile / scalafmtCheckAll)).value
  )
