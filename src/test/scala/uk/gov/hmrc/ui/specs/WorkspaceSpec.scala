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
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CreateThreadPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.AcceptanceTests

class WorkspaceSpec extends BaseSpec {
  Feature("Internal User Journey - Workspace ") {

    Scenario("Get Landing Page with correct Role for test user", AcceptanceTests) {

      Given("User Logins with correct role")
      AuthLoginPage.navigateToAuthPage()
      AuthLoginPage.enterPIDValue("123456")
      AuthLoginPage.enterGivenNameValue("test")
      AuthLoginPage.enterSurNameValue("user")
      AuthLoginPage.enterEmailAddressValue("test.user@gmail.com")
      AuthLoginPage.selectStatusSuccess()
      AuthLoginPage.selectSignatureValid()
      AuthLoginPage.enterRolesText("sdec_integration_tester")
      AuthLoginPage.selectConfirmAndSendButton()

      When("the dashboard page loads")

      Then("""a "Create thread" button must be displayed""")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true
    }

    Scenario("View Thread Information", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.navigateToAuthPage()
      AuthLoginPage.enterPIDValue("123456")
      AuthLoginPage.enterGivenNameValue("test")
      AuthLoginPage.enterSurNameValue("user")
      AuthLoginPage.enterEmailAddressValue("test.user@gmail.com")
      AuthLoginPage.selectStatusSuccess()
      AuthLoginPage.selectSignatureValid()
      AuthLoginPage.enterRolesText("sdec_integration_tester")
      AuthLoginPage.selectConfirmAndSendButton()

      When("the dashboard page loads for the Test User")

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

    Scenario("View Thread status for a specific Thread ", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.navigateToAuthPage()
      AuthLoginPage.enterPIDValue("123456")
      AuthLoginPage.enterGivenNameValue("test")
      AuthLoginPage.enterSurNameValue("user")
      AuthLoginPage.enterEmailAddressValue("test.user@gmail.com")
      AuthLoginPage.selectStatusSuccess()
      AuthLoginPage.selectSignatureValid()
      AuthLoginPage.enterRolesText("sdec_qa_tester")
      AuthLoginPage.selectConfirmAndSendButton()

      When("the dashboard page loads for the Test User")

      Then("the Test User views the status for specific Thread Ref No. -THR-2026-0616-0003 ")

      WorkspacePage.getStatusValueText shouldBe "Overdue"

      And("the Test User views the other thread status with priority work")

    }

    Scenario("Test user opens an active thread and validates and reviews details", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.navigateToAuthPage()
      AuthLoginPage.enterPIDValue("123456")
      AuthLoginPage.enterGivenNameValue("test")
      AuthLoginPage.enterSurNameValue("user")
      AuthLoginPage.enterEmailAddressValue("test.user@gmail.com")
      AuthLoginPage.selectStatusSuccess()
      AuthLoginPage.selectSignatureValid()
      AuthLoginPage.enterRolesText("sdec_qa_tester")
      AuthLoginPage.selectConfirmAndSendButton()

      When("the dashboard page loads for the Test User")

      Then("the Test User selects first active thread")
      val threadDetails: List[String] = WorkspacePage.getThreadDetails
      WorkspacePage.selectFirstThreadReference()

      And("the Test User verifies thread details")
      WorkspacePage.getThreadReferenceValue  should include(threadDetails.head)
      WorkspacePage.getRelatedReferenceValue should include(threadDetails(1))
      WorkspacePage.getExternalContactValue  should include(threadDetails(2))
      WorkspacePage.getStatusValue           should include(threadDetails(3))
      WorkspacePage.getWaitingOnValue        should include(threadDetails(4))
    }

  }
}
