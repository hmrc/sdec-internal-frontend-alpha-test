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
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CheckYourAnswersPage, CreateThreadPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.{AcceptanceTests, SoloTests}

class CheckYourAnswersSpec extends BaseSpec {
  Feature("Internal User Journey - Check Your Answers Page") {

    Scenario(
      "The Test User selects No for existing case option, then Related Case Reference row will not show in Check your answers page",
      AcceptanceTests
    ) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the correct details")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" cc774572d")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("")
      CreateThreadPage.selectHasRelatedCaseNo()
      CreateThreadPage.selectContinueButton()

      Then("the Test User edits the date of reply and verifies the updated date in check your answers")
      CreateThreadPage.getCreateThreadPageTitleText     shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.selectThreadDetailsLink()
      CreateThreadPage.getCreateThreadPageTitleText     shouldBe "Thread details"
      CreateThreadPage.enterDate("11", "12", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.getCheckYourAnswersTitleText shouldBe "Check your answers"
      CheckYourAnswersPage.getDateUpdateText            shouldBe "11 December 2026"
      //      CheckYourAnswersPage.relatedReferenceNumberElements shouldBe empty

    }

    Scenario("The Test User successfully submits the Check your Answers page ", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the correct details")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" cc774572d")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      Then("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.selectConfirmAndSendButton()
    }

    Scenario("The Test User validates contact details and date in Check your answers page", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the correct details")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" cc774572d")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      Then("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.getNameUpdateText          should include("Steffi Graf")
      CheckYourAnswersPage.getEmailAddressText        should include("steffi@abc.com")
      CheckYourAnswersPage.getMobileNumberText        should include("123456789")
      CheckYourAnswersPage.getNINumberText            should include("CC 77 45 72 D")
      CheckYourAnswersPage.getDateUpdateText          should include("11 November 2026")
    }

    Scenario("The Test User successfully amends the name in who are you contacting page", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the correct details")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" cc774572d")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      Then("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.selectWhoAreYouContactingLink()
      CreateThreadPage.getCreateThreadPageTitleText   should include("Who are you contacting?")
      CreateThreadPage.enterFirstNameValue("Sam")
      CreateThreadPage.selectContinueButton()
      CreateThreadPage.getCreateThreadPageTitleText shouldBe "Thread details"
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.getNameUpdateText        shouldBe "Sam Graf"

    }

    Scenario("The Test User successfully amends the date in check your Answers page", AcceptanceTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the correct details")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" cc774572d")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      Then("the Test User edits the date of reply and verifies the updated date in check your answers")
      CreateThreadPage.getCreateThreadPageTitleText     shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.selectThreadDetailsLink()
      CreateThreadPage.getCreateThreadPageTitleText     shouldBe "Thread details"
      CreateThreadPage.enterDate("11", "12", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      CheckYourAnswersPage.getCheckYourAnswersTitleText shouldBe "Check your answers"
      CheckYourAnswersPage.getDateUpdateText            shouldBe "11 December 2026"

    }

  }
}
