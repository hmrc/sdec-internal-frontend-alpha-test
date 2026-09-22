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

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.{By, JavascriptExecutor, WebElement}
import uk.gov.hmrc.ui.pages.AuthLoginPage.driver

object CheckYourAnswersPage extends BasePage {

  val clickConfirmAndSubmitButton: By = By.xpath(
    "//button[@type='submit' and @class='govuk-button' and @data-module='govuk-button' and contains(text(), 'Confirm and send')]"
  )
  val whoAreYouContactingPage: By = By.cssSelector(
    "dl.govuk-summary-list > div:nth-child(1) > dd.govuk-summary-list__actions > a.govuk-link"
  ) // *[@id="main-content"]/div/div/form/h1//*[@id="main-content"]/div[2]/div/dl[1]/div[1]/dd[2]/a
  val threadDetailsPage: By = By.cssSelector(
    "dl.govuk-summary-list:nth-of-type(2) > div:nth-child(1) > dd.govuk-summary-list__actions > a.govuk-link"
  )
  val checkYourAnswersPage: By = By.cssSelector("h1.govuk-heading-l")
  val verifyNameUpdate:     By = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[1]/div[1]/dd[1]")
  val verifyDateUpdate:     By = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[2]/div[2]/dd[1]")
  val theirNameValue:    By = By.cssSelector("dl.govuk-summary-list > div:nth-child(1) > dd.govuk-summary-list__value")
  val emailAddressValue: By = By.cssSelector("dl.govuk-summary-list > div:nth-child(2) > dd.govuk-summary-list__value")
  val mobileNumberValue: By = By.cssSelector("dl.govuk-summary-list > div:nth-child(3) > dd.govuk-summary-list__value")
  val niNumberValue:     By = By.cssSelector("dl.govuk-summary-list > div:nth-child(4) > dd.govuk-summary-list__value")
  val relatedCaseValue:  By = By.cssSelector("dl.govuk-summary-list > div:nth-child(5) > dd.govuk-summary-list__value")
  val relatedReferenceNumberValue: By =
    By.cssSelector("dl.govuk-summary-list > div:nth-child(6) > dd.govuk-summary-list__value")
  val messageValue:                 By = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[2]/div[1]/dd[1]/text()")
  val threadReferenceNumberLocator: By = By.cssSelector(".govuk-caption-l")
  val responseRequiredDateLocator:  By = By.cssSelector("p.govuk-body strong")
  val statusLocator:                By = By.cssSelector("h2.hmrc-timeline__event-title")

  def getThreadReferenceNumberText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(threadReferenceNumberLocator)).getText.trim

  def getResponseRequiredDateText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(responseRequiredDateLocator)).getText.trim

  def getStatusText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusLocator)).getText.trim

  def getNameUpdateText: String = {
    val nameElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(verifyNameUpdate)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def relatedReferenceNumberElements: java.util.List[WebElement] =
    driver.findElements(relatedReferenceNumberValue)

  def getMessageText: String = {
    val nameElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(messageValue)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def getEmailAddressText: String = {
    val nameElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(emailAddressValue)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def getMobileNumberText: String = {
    val nameElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(mobileNumberValue)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def getNINumberText: String = {
    val nameElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(niNumberValue)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def getDateUpdateText: String = {
    val dateElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(verifyDateUpdate)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dateElement)

    val dateText = dateElement.getText.trim()

    dateText
  }

  def getCheckYourAnswersTitleText: String = {
    val headingElement = webDriverWait.until(
      ExpectedConditions.presenceOfElementLocated(checkYourAnswersPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", headingElement)

    val headingText = headingElement.getText.trim()

    headingText
  }

  def selectThreadDetailsLink(): Unit = {
    val changeLink = webDriverWait.until(
      ExpectedConditions.elementToBeClickable(threadDetailsPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", changeLink)

    webDriverWait.until(ExpectedConditions.elementToBeClickable(changeLink))

    jsExecutor.executeScript("arguments[0].click();", changeLink)
  }

  def selectWhoAreYouContactingLink(): Unit = {
    val changeLink = webDriverWait.until(
      ExpectedConditions.elementToBeClickable(whoAreYouContactingPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", changeLink)

    webDriverWait.until(ExpectedConditions.elementToBeClickable(changeLink))

    jsExecutor.executeScript("arguments[0].click();", changeLink)
  }

  def selectConfirmAndSendButton(): Unit = {
    val confirmAndSendButton = webDriverWait.until(
      ExpectedConditions.elementToBeClickable(clickConfirmAndSubmitButton)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", confirmAndSendButton)

    webDriverWait.until(ExpectedConditions.elementToBeClickable(confirmAndSendButton))

    jsExecutor.executeScript("arguments[0].click();", confirmAndSendButton)
  }

}
