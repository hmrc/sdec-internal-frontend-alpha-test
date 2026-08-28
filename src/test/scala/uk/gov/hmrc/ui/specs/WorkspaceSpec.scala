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

import org.scalatest.featurespec.AnyFeatureSpec
import uk.gov.hmrc.ui.pages.{AuthLoginPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

class WorkspaceSpec extends BaseSpec {
  Feature("Internal User Journey - Workspace ") {

    Scenario("Get Landing Page", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()

      When("the dashboard page loads for the Test user")

      Then("the Test User should be able to view a dashboard page ")
      WorkspacePage.getHeading.getText should include("Share Files Securely with Child Benefit Service")

      And("the Test User should be able to navigate to Workspace tab available ")
      WorkspacePage.getWorkspaceTab.getText should include("Workspace")

    }

    Scenario("View Thread Information", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()

      When("the dashboard page loads for the Test User")
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      Then("""the thread information details are displayed in a table with title "shared work queue"""")
      WorkspacePage.getThreadInformationText should include("Shared work queue")

      And("the table has Thread Reference, Related Reference, External Contact, Status, Waiting on and Deadline")
      WorkspacePage.getThreadReferenceText  shouldBe "Thread reference"
      WorkspacePage.getRelatedReferenceText shouldBe "Related reference"
      WorkspacePage.getExternalContactText  shouldBe "External contact"
      WorkspacePage.getStatusText           shouldBe "Status"
      WorkspacePage.getWaitingOnText        shouldBe "Waiting on"
      WorkspacePage.getDeadlineText         shouldBe "Deadline"

    }

    Scenario("View Thread status for for a specific Thread ", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()

      When("the dashboard page loads for the Test User")

      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      Then("the Test User views the status for specific Thread Ref No. -THR-2026-0616-0003 ")

      WorkspacePage.getStatusValueText shouldBe "Overdue"

      And("the Test User views the other thread status with priority work")

      WorkspacePage.getStatusValueNeedsAttentionText shouldBe "Needs action"

    }
  }
}
