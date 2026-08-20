/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CreateThreadPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

import java.time.Duration

class CreateThreadSpec extends BaseSpec {
  Feature("Internal User Journey - Create Thread page") {

    Scenario("Create Thread Button is visible and must be selectable", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("""the Test User views a "Create thread" button """)
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true

      And("the button must follow GOV.UK Design System standards")
      CreateThreadPage.getThreadButtonText should include("Create new thread")

      Then("the Test User clicks on the create thread button it should navigate to the creation page")

    }

  }
}
