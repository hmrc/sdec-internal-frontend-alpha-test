import sbt.*

object Dependencies {

  val test: Seq[ModuleID] = Seq(
    "uk.gov.hmrc" %% "ui-test-runner" % "0.54.0" % Test,
    "uk.gov.hmrc" %% "domain-play-30" % "12.1.0" % Test
  )

}
