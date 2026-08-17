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

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.{AuthLoginPage, WorkspacePage}
import uk.gov.hmrc.ui.pages.AuthLoginPage.{driver, login}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

import java.time.Duration

class WorkspaceSpec extends BaseSpec {
  Feature("Internal User Journey") {

    Scenario("Get Landing Page", AcceptanceTests) {

      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      AuthLoginPage.login()

      When("the dashboard page loads")

      Then("the system must display a dashboard page layout")
      WorkspacePage.getHeading.getText should include("Share Files Securely with Child Benefit Service")

      And("the system must display a navigation area")
      WorkspacePage.getWorkspaceTab.getText should include("Workspace")

    }

    Scenario("Create Thread Button Display", AcceptanceTests) {

      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      AuthLoginPage.login()

      When("the dashboard page loads")

      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      Then("""a "Create thread" button must be displayed""")

      WorkspacePage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      WorkspacePage.isCreateThreadButtonEnabled shouldBe true

      And("the button must follow GOV.UK Design System standards")
      WorkspacePage.getThreadButtonText should include("Create new thread")

    }

    Scenario("View Thread Information", AcceptanceTests) {

      Given("User Logins with Credential ID") // This might be the wrong way for internal HMRC staff to login for now
      AuthLoginPage.login()

      When("the dashboard page loads")

      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      Then("""the thread information details are displayed in a table with title "shared work queue"""")

      WorkspacePage.getThreadInformationText should include("Shared work queue")

      And("the table has Thread Reference, Related Reference, External Contact, Status, Waiting on and Deadline")

      WorkspacePage.getThreadReferenceText  shouldBe "Thread Reference"
      WorkspacePage.getRelatedReferenceText shouldBe "Related reference"
      WorkspacePage.getExternalContactText  shouldBe "External contact"
      WorkspacePage.getStatusText           shouldBe "Status"
      WorkspacePage.getWaitingOnText        shouldBe "Waiting on"
      WorkspacePage.getDeadlineText         shouldBe "Deadline"

    }

  }
}
