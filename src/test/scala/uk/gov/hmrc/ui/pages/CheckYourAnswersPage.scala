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

object CheckYourAnswersPage extends BasePage {

  val clickConfirmAndSubmitButton: By = By.xpath(
    "//button[@type='submit' and @class='govuk-button' and @data-module='govuk-button' and contains(text(), 'Confirm and send')]"
  )
  val whoAreYouContactingPage: By     = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[1]/div[1]/dd[2]/a")
  val threadDetailsPage: By           = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[2]/div[1]/dd[2]/a")
  val checkYourAnswersPage: By        = By.xpath("//*[@id=\"main-content\"]/div[1]/div/h1")
  val verifyNameUpdate: By            = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[1]/div[1]/dd[1]")
  val verifyDateUpdate: By            = By.xpath("//*[@id=\"main-content\"]/div[2]/div/dl[2]/div[2]/dd[1]")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(20))
  
  def getNameUpdateText: String = {
    val nameElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(verifyNameUpdate)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", nameElement)

    val nameText = nameElement.getText.trim()

    nameText
  }

  def getDateUpdateText: String = {
    val dateElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(verifyDateUpdate)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", dateElement)

    val dateText = dateElement.getText.trim()

    dateText
  }

  def getCheckYourAnswersTitleText: String = {
    val headingElement = wait.until(
      ExpectedConditions.presenceOfElementLocated(checkYourAnswersPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", headingElement)

    val headingText = headingElement.getText.trim()

    headingText
  }

  def selectThreadDetailsLink(): Unit = {
    val changeLink = wait.until(
      ExpectedConditions.elementToBeClickable(threadDetailsPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", changeLink)

    wait.until(ExpectedConditions.elementToBeClickable(changeLink))

    jsExecutor.executeScript("arguments[0].click();", changeLink)
  }

  def selectWhoAreYouContactingLink(): Unit = {
    val changeLink = wait.until(
      ExpectedConditions.elementToBeClickable(whoAreYouContactingPage)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", changeLink)

    wait.until(ExpectedConditions.elementToBeClickable(changeLink))

    jsExecutor.executeScript("arguments[0].click();", changeLink)
  }

  def selectConfirmAndSendButton(): Unit = {
    val confirmAndSendButton = wait.until(
      ExpectedConditions.elementToBeClickable(clickConfirmAndSubmitButton)
    )

    val jsExecutor = driver.asInstanceOf[JavascriptExecutor]
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", confirmAndSendButton)

    wait.until(ExpectedConditions.elementToBeClickable(confirmAndSendButton))

    jsExecutor.executeScript("arguments[0].click();", confirmAndSendButton)
  }

}
