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
import org.scalatest.prop.TableDrivenPropertyChecks
import uk.gov.hmrc.ui.TestData.AuthTestData
import uk.gov.hmrc.ui.pages.{AuthLoginPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.{AcceptanceTests, SoloTests}

class WorkspaceSpec extends BaseSpec with TableDrivenPropertyChecks {
  Feature("Internal User Journey - Workspace ") {

    Scenario("Threads created by the Child benefits user can be filtered ", AcceptanceTests) {
      forAll(AuthTestData.usersWithChildBenefits) { (pid, givenName, surName, email, roles) =>
        Given("Child Benifits User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads")

        Then("The user clicks the My Threads Filter button to view all the threads created by user")
        WorkspacePage.selectMyThreadsFilterButton()

        And("The user is able to open the thread to continue working")
        WorkspacePage.getMyThreadsChildBenefitsFilterText shouldBe "THREAD1000AA"
        WorkspacePage.clickFirstThreadId("THREAD1000AA")
      }
    }

    Scenario("Threads created by the Child benefits user can be cleared ", AcceptanceTests) {
      forAll(AuthTestData.usersWithChildBenefits) { (pid, givenName, surName, email, roles) =>
        Given("Child Benifits User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads")

        Then("The user clicks the My Threads Filter button to view all the threads created by user")
        WorkspacePage.selectMyThreadsFilterButton()

        And("The user clicks the ClearThreads button to clear the filter")
        WorkspacePage.selectClearThreadsFilterButton()

      }
    }

    Scenario("The Pensions User Views Thread Information", AcceptanceTests) {

      forAll(AuthTestData.usersWithPensions) { (pid, givenName, surName, email, roles) =>
        Given("Pensions User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads for the Test User")

        And("""the thread information details are displayed in a table with title "shared work queue"""")
        WorkspacePage.getWorkspaceHeadingText should include("Shared work queue")

        And("the table has Thread Reference, Related Reference, External Contact, Status, Waiting on and Deadline")
        WorkspacePage.getThreadReferenceHeader  shouldBe "Thread reference"
        WorkspacePage.getRelatedReferenceHeader shouldBe "Related reference"
        WorkspacePage.getExternalContactHeader  shouldBe "External contact"
        WorkspacePage.getStatusHeader           shouldBe "Status"
        WorkspacePage.getWaitingOnHeader        shouldBe "Waiting on"
        WorkspacePage.getDeadlineHeader         shouldBe "Deadline"

        Then("The user clicks the My Threads Filter button to view all the threads created by user")
        WorkspacePage.selectMyThreadsFilterButton()
        WorkspacePage.clickFirstThreadId("THREAD4000DD")

      }
    }
    Scenario("User is both pensions and child benefit user filter threads  ", AcceptanceTests) {

      forAll(AuthTestData.usersWithBothRoles) { (pid, givenName, surName, email, roles) =>
        Given("Pensions and child Benefits User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads for the Test User")
        WorkspacePage.getStatusValueText shouldBe "Overdue"

        Then("The user clicks the My Threads Filter button to view all the threads created by user")
        WorkspacePage.selectMyThreadsFilterButton()
        WorkspacePage.clickFirstThreadId("THREAD5000EE")

      }
    }

    Scenario("User with no role and not created tickets clicks the My Filters button ", AcceptanceTests) {

      forAll(AuthTestData.usersWithNoRoles) { (pid, givenName, surName, email, roles) =>
        Given("User with no roles Logs in ")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads for the Test User")

        Then("the User selects first active thread")
        val threadDetails: List[String] = WorkspacePage.getThreadDetails
        WorkspacePage.selectFirstThreadReference()

        And("the User verifies thread details")
        WorkspacePage.getSpecificThreadReferenceText  shouldBe threadDetails.head
        WorkspacePage.getSpecificRelatedReferenceText shouldBe threadDetails(1)
        WorkspacePage.getSpecificExternalContactText  shouldBe threadDetails(2)
        WorkspacePage.getSpecificStatusText           shouldBe threadDetails(3)
        WorkspacePage.getSpecificWaitingOnText        shouldBe threadDetails(4)

      }
    }
    Scenario("User with tax role created tickets clicks the My Filters button ", AcceptanceTests) {

      forAll(AuthTestData.usersWithTax) { (pid, givenName, surName, email, roles) =>
        Given("User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads for the Test User")

        Then("the User clicks on MyThreads Filters")
        WorkspacePage.selectMyThreadsFilterButton()
        WorkspacePage.getNoThreadsAvailableMessage shouldBe "There are no threads matching the filter you applied."
      }
    }
    Scenario("User with No role created tickets clicks the My Filters button ", AcceptanceTests) {

      forAll(AuthTestData.usersWithNoRoles) { (pid, givenName, surName, email, roles) =>
        Given("User Logins with correct role")
        AuthLoginPage.navigateToAuthPage()
        AuthLoginPage.enterPIDValue(pid)
        AuthLoginPage.enterGivenNameValue(givenName)
        AuthLoginPage.enterLastNameValue(surName)
        AuthLoginPage.enterEmailAddressValue(email)
        AuthLoginPage.selectStatusSuccess()
        AuthLoginPage.selectSignatureValid()
        AuthLoginPage.enterRolesText(roles)
        AuthLoginPage.selectConfirmAndSendButton()

        When("the dashboard page loads for the Test User")

        Then("the User clicks on MyThreads Filters")
        WorkspacePage.selectMyThreadsFilterButton()
        WorkspacePage.getNoThreadsAvailableMessage shouldBe "There are no threads matching the filter you applied."
      }
    }
  }
}
