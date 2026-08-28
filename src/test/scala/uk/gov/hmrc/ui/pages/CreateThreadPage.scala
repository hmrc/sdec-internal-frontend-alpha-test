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

package uk.gov.hmrc.ui.pages

import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, JavascriptExecutor, WebElement}
import uk.gov.hmrc.domain.{Generator, Nino}
import uk.gov.hmrc.ui.pages.AuthLoginPage.driver

import java.time.Duration
import scala.jdk.CollectionConverters.*
import scala.math.random

object CreateThreadPage extends BasePage {

  val createThreadButtonLocator: By    = By.xpath("//*[@id=\"main-content\"]/div/div[1]/a")
  val introTextLocator: By             = By.xpath("//*[contains(normalize-space(), 'Share Files Securely')]")
  val createThreadPageTitle: By        = By.xpath("//*[@id=\"main-content\"]/div/div/form/h1")
  val enterFirstName: By               = By.id("firstName")
  val enterLastName: By                = By.id("lastName")
  val enterEmailAddress: By            = By.id("email")
  val enterPhoneNumber: By             = By.id("phoneNumber")
  val enterNationalInsuranceNumber: By = By.id("nationalInsuranceNumber")
  val clickYesExistingCase: By         = By.id("hasRelatedCase")
  val enterRelatedRefNo: By            = By.id("caseReferenceNumber")
  val viewNoExistingCase: By           = By.id("hasRelatedCase-hint")
  val clickNoExistingCase: By          = By.id("hasRelatedCase-no")
  val clickContinueButton: By          = By.cssSelector(
    "button.govuk-button[type='submit'][data-module='govuk-button']"
  )
  val errorFirstName: By               = By.id("firstName-error")
  val errorLastName: By                = By.id("lastName-error")
  val errorEmailAddress: By            = By.id("email-error")
  val errorPhoneNumber: By             = By.id("phoneNumber-error")
  val errorNationalInsuranceNumber: By = By.id("nationalInsuranceNumber-error")
  val threadDetailPageTitle: By        = By.xpath("//*[@id=\"main-content\"]/div/div/form/h1")
  val addMessageDetails: By            = By.id("message")
  val clickSubmitButton: By            = By.cssSelector("#main-content > div > div > form > button")
  val remainingCharactersLeft: By      = By.xpath("//*[@id=\"main-content\"]/div/div/form/div[2]/div[3]")
  val overTheLimitCharLink: By         = By.xpath("//a[@href='#message' and contains(text(), 'Message must be')]")
  val overTheLimitCharMessage: By      = By.cssSelector("p#message-error.govuk-error-message")
  val dayPartOfDate: By                = By.id("responseDate-responseDate.day")
  val monthPartOfDate: By              = By.id("responseDate-responseDate.month")
  val yearPartOfDate: By               = By.id("responseDate-responseDate.year")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(20))

  def getCreateThreadButton: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getIntroductoryText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getText.trim

  def getRemainingCharacterCountDisplayed: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(remainingCharactersLeft)).getText.trim

  def getThreadDetailPageText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(threadDetailPageTitle)).getText.trim

  def getViewNoExistingCaseText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(viewNoExistingCase)).getText.trim

  def getCreateThreadPageTitleText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadPageTitle)).getText.trim

  def getErrorFirstName: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(errorFirstName)).getText.trim

  def getErrorLastName: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(errorLastName)).getText.trim

  def getErrorEmailAddress: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(errorEmailAddress)).getText.trim

  def getErrorPhoneNumber: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(errorPhoneNumber)).getText.trim

  def getErrorNationalInsuranceNumber: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(errorNationalInsuranceNumber)).getText.trim

  def getMessageDetails: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(addMessageDetails))

  def getEnterFirstNameInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterFirstName))

  def getEnterLastNameInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterLastName))

  def getEnterEmailInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterEmailAddress))

  def getEnterPhoneNumberInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterPhoneNumber))

  def getEnterNationalInsuranceNumberInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterNationalInsuranceNumber))

  def getEnterRelatedRefNoInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(enterRelatedRefNo))

  def getClickYesExistingCaseInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(clickYesExistingCase))

  def getSubmitButtonInput: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(clickSubmitButton))

  def dayPartOfDateElement: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(dayPartOfDate))

  def monthPartOfDateElement: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(monthPartOfDate))

  def yearPartOfDateElement: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(yearPartOfDate))

  def enterDayPartOfDate(value: String): Unit = {
    val input = dayPartOfDateElement
    input.clear()
    input.sendKeys(value)
  }

  def enterMonthPartOfDate(value: String): Unit = {
    val input = monthPartOfDateElement
    input.clear()
    input.sendKeys(value)
  }

  def enterYearPartOfDate(value: String): Unit = {
    val input = yearPartOfDateElement
    input.clear()
    input.sendKeys(value)
  }

  def enterMessageDetails(value: String): Unit = {
    val input = getMessageDetails
    input.clear()
    input.sendKeys(value)
  }

  def enterFirstNameValue(value: String): Unit = {
    val input = getEnterFirstNameInput
    input.clear()
    input.sendKeys(value)
  }

  def enterLastNameValue(value: String): Unit = {
    val input = getEnterLastNameInput
    input.clear()
    input.sendKeys(value)
  }

  def enterEmailAddressValue(value: String): Unit = {
    val input = getEnterEmailInput
    input.clear()
    input.sendKeys(value)
  }

  def enterPhoneNumberValue(value: String): Unit = {
    val input = getEnterPhoneNumberInput
    input.clear()
    input.sendKeys(value)
  }

  def enterNationalInsuranceValue(value: String): Unit = {
    val input = getEnterNationalInsuranceNumberInput
    input.clear()
    input.sendKeys(value)
  }

  def enterRelatedRefNoValue(value: String): Unit = {
    val input = getEnterRelatedRefNoInput
    input.clear()
    input.sendKeys(value)
  }

  def isCreateThreadButtonDisplayed: Boolean =
    driver.findElements(createThreadButtonLocator).asScala.nonEmpty &&
      getCreateThreadButton.isDisplayed

  def isCreateThreadButtonEnabled: Boolean =
    getCreateThreadButton.isEnabled

  def getThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

  def selectSubmitMessageDetailsButton(): Unit =
    getSubmitButtonInput.click()

  def selectClickYesButton(): Unit =
    getClickYesExistingCaseInput.click()

  def selectContinueButton(): Unit = {
    val continueButton = wait.until(
      ExpectedConditions.elementToBeClickable(clickContinueButton)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", continueButton)

    wait.until(ExpectedConditions.elementToBeClickable(continueButton))

    jsExecutor.executeScript("arguments[0].click();", continueButton)
  }

  def isIntroTextDisplayedBeforeButton: Boolean = {
    val introLocation  = wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getLocation
    val buttonLocation = getCreateThreadButton.getLocation
    introLocation.getY < buttonLocation.getY
  }

  def errorMessageText(): String = {
    val errorMessage = wait.until(
      ExpectedConditions.presenceOfElementLocated(overTheLimitCharMessage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", errorMessage)

    val messageText = errorMessage.getText

    messageText
  }

  def clickErrorMessageLink(): Unit = {
    val errorMessageLink = wait.until(
      ExpectedConditions.visibilityOfElementLocated(overTheLimitCharLink)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", errorMessageLink)

    try
      errorMessageLink.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", errorMessageLink)
    }

  }

  def getClickNoExistingCaseInput: Boolean = {
    val radioElement = wait.until(
      ExpectedConditions.visibilityOfElementLocated(clickNoExistingCase)
    )

    val radioLocation = radioElement.getLocation
    val radioSize     = radioElement.getSize

    radioElement.isDisplayed &&
    radioSize.getWidth > 0 &&
    radioSize.getHeight > 0 &&
    radioLocation.getY > 0
  }

  def selectHasRelatedCaseNo(): Unit = {
    val radioElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(clickNoExistingCase)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioElement)

    try
      radioElement.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", radioElement)
    }

  }

  def getClickHasRelatedCaseInput: Boolean = {
    val radioElement = wait.until(
      ExpectedConditions.visibilityOfElementLocated(clickYesExistingCase)
    )

    val radioLocation = radioElement.getLocation
    val radioSize     = radioElement.getSize

    radioElement.isDisplayed &&
    radioSize.getWidth > 0 &&
    radioSize.getHeight > 0 &&
    radioLocation.getY > 0
  }

  def selectHasRelatedCaseYes(): Unit = {
    val radioElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(clickYesExistingCase)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radioElement)

    try
      radioElement.click()
    catch {
      case _: Exception =>
        jsExecutor.executeScript("arguments[0].click();", radioElement)
    }
  }

}
