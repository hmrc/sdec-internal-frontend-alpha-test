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
import uk.gov.hmrc.ui.pages.CreateThreadPage.getClickNoExistingCaseInput
import uk.gov.hmrc.ui.pages.{AuthLoginPage, CheckYourAnswersPage, CreateThreadPage, WorkspacePage}
import uk.gov.hmrc.ui.specs.tags.{AcceptanceTests, CreateThreadTests, SoloTests}

import java.time.Duration

class CreateThreadSpec extends BaseSpec {
  Feature("Internal User Journey - Create Thread page") {

    Scenario("Create Thread Button is visible and must be selectable", CreateThreadTests) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("""the Test User views a "Create thread" button """)
      CreateThreadPage.isCreateThreadButtonDisplayed shouldBe true

      And("the button must be selectable")
      CreateThreadPage.isCreateThreadButtonEnabled shouldBe true

      And("the button must follow GOV.UK Design System standards")
      CreateThreadPage.getThreadButtonText should include("Create new thread")

      Then("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

    }

    Scenario(
      "The Test User enters the contact details incorrectly and validation message captured",
      CreateThreadTests
    ) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      Then("the Test User does not enter the details and the error message captured")

      CreateThreadPage.enterFirstNameValue("")
      CreateThreadPage.enterLastNameValue("")
      CreateThreadPage.enterEmailAddressValue("")
      CreateThreadPage.enterPhoneNumberValue("")
      CreateThreadPage.enterNationalInsuranceValue("")
      CreateThreadPage.selectContinueButton()
      CreateThreadPage.getErrorFirstName               should include("Enter a first name")
      CreateThreadPage.getErrorLastName                should include("Enter a last name")
      CreateThreadPage.getErrorEmailAddress            should include("Enter an email address")
      CreateThreadPage.getErrorPhoneNumber             should include("Enter a mobile number")
      CreateThreadPage.getErrorNationalInsuranceNumber should include("Enter a National Insurance number")

    }

    Scenario(
      "The Test User enters the contact details with invalid format for National Insurance Number",
      CreateThreadTests
    ) {

      Given("Test User Logins with Credential ID")
      AuthLoginPage.login()
      WorkspacePage.getWorkspaceTab.getText shouldBe "Workspace"

      When("the Test User clicks on the create thread button it should navigate to the create new thread page")
      CreateThreadPage.selectCreateThreadButton()
      CreateThreadPage.getCreateThreadPageTitleText should include("Who are you contacting?")

      And("the Test User enters the incorrect National insurance number")
      CreateThreadPage.enterFirstNameValue("Steffi")
      CreateThreadPage.enterLastNameValue("Graf")
      CreateThreadPage.enterEmailAddressValue("steffi@abc.com")
      CreateThreadPage.enterPhoneNumberValue("123456789")
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 ")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseNo()
      CreateThreadPage.selectContinueButton()

      Then("the Test user gets a error message for entering the correct National Insurance number")
      CreateThreadPage.getErrorNationalInsuranceNumber should include(
        "Enter a National Insurance number in the correct format, like QQ 12 34 56 C"
      )

    }

    Scenario(
      "The Test User enters the contact details and adds message details with character limit and yes related case",
      CreateThreadTests
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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.selectSubmitMessageDetailsButton()

    }

    Scenario(
      "The Test User enters the contact details and adds message details with character limit exceeded and no related case",
      CreateThreadTests
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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      And("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseNo()

      CreateThreadPage.selectContinueButton()

      And(
        "the Test User adds a message to the external user exceeded the character limit available and user tries to submit"
      )
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum " +
          "sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies " +
          "nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla " +
          "vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. " +
          "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper " +
          "nisiLorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. " +
          "Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, " +
          "venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. " +
          "Vivamus elementum semper nisi."
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 65 characters too many")
      CreateThreadPage.selectSubmitMessageDetailsButton()

      And("the Test User gets an error message which is captured")
      CreateThreadPage.clickErrorMessageLink()
      val errorMessage = CreateThreadPage.errorMessageText()
      errorMessage should include("Message must be 1,000 characters or less. You have 65 characters too many")
    }

    Scenario("The Test User enters the contact details and clicks yes for related case", CreateThreadTests) {

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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.getViewNoExistingCaseText should include(
        "Select Yes if this communication is linked to an existing case"
      )
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

    }

    Scenario("Test user creates a new thread and provides valid response date for deadline", CreateThreadTests) {

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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.enterDate("11", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User navigates to Check Your Answers page")
      CheckYourAnswersPage.getCheckYourAnswersTitleText    should include("Check your answers")

    }

    Scenario(
      "Test user creates a new thread and leaves one or more of day, month or year boxes empty",
      CreateThreadTests
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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.enterDate("", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter the day part of the date")
      CreateThreadPage.enterDate("11", "", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter the month part of the date")
      CreateThreadPage.enterDate("11", "11", "")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter the year part of the date")

    }

    Scenario("Test user creates a new thread and provides unreal value for day, month and year", CreateThreadTests) {

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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.enterDate("33", "11", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter a valid response date")
      CreateThreadPage.enterDate("11", "14", "2026")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter a valid response date")

    }

    Scenario(
      "Test user creates a new thread and provides response date in past, receives error message that response date must be a future date",
      CreateThreadTests
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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.enterDate("11", "11", "2025")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("The response date must be in the future")
    }

    Scenario(
      "Test user creates a new thread and provides less than 4 characters in year field for response date, receives error message - Enter the year using 4 digits ",
      CreateThreadTests
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
      CreateThreadPage.enterNationalInsuranceValue(" SL 67 55 80 A")

      Then("the Test User enters the no continue button")
      CreateThreadPage.selectHasRelatedCaseYes()
      CreateThreadPage.enterRelatedRefNoValue("QQ 12 34 56 C")
      CreateThreadPage.selectContinueButton()

      And("the Test User adds a message to the external user within the character limit available")
      CreateThreadPage.getCreateThreadPageTitleText      shouldBe "Thread details"
      CreateThreadPage.enterMessageDetails(
        "Lorem ipsum dolor sit amet, " +
          "consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
          "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
          "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, " +
          "pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. " +
          "Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. " +
          "In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam " +
          "dictum felis eu pede mollis pretium. Integer tincidunt. " +
          "Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. " +
          "Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. " +
          "Aliquam lorem ante, dapibus in, viverra quis, feugiat a,"
      )
      CreateThreadPage.getRemainingCharacterCountDisplayed should include("You have 307 characters remaining")
      CreateThreadPage.enterDate("11", "11", "26")
      CreateThreadPage.selectSubmitMessageDetailsButton()
      And("the Test User sees the response date error message")
      CreateThreadPage.checkResponseDateErrorMessage()     should include("Enter the year using 4 digits")
    }

  }
}
