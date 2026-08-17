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

import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import uk.gov.hmrc.ui.pages.AuthLoginPage.driver
import scala.jdk.CollectionConverters.*
import java.time.Duration

object WorkspacePage extends BasePage {

  val introTextLocator: By          = By.xpath("//*[contains(normalize-space(), 'Share Files Securely')]")
  val createThreadButtonLocator: By = By.xpath(
    "//*[@id=\"main-content\"]/div/div[1]/a"
  )
  val heading: By                   = By.xpath("/html/body/header/div/div[2]/a")
  val workspaceTab: By              = By.xpath("/html/body/div/nav/div/a")
  val threadInformationText: By     = By.xpath("//*[@id=\"main-content\"]/div/h2")
  val threadReferenceText: By       = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[1]")
  val relatedReferenceText: By      = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[2]")
  val externalContactText: By       = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[3]")
  val statusText: By                = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[4]")
  val waitingOnText: By             = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[5]")
  val deadlineText: By              = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[6]")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(10))

  def getIntroductoryText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getText.trim

  def getCreateThreadButton: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getThreadInformationText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(threadInformationText)).getText.trim

  def getThreadReferenceText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(threadReferenceText)).getText.trim

  def getRelatedReferenceText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(relatedReferenceText)).getText.trim

  def getExternalContactText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(externalContactText)).getText.trim

  def getStatusText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(statusText)).getText.trim

  def getWaitingOnText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(waitingOnText)).getText.trim

  def getDeadlineText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(deadlineText)).getText.trim

  def getHeading: WebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(heading))

  def getWorkspaceTab: WebElement = wait.until(ExpectedConditions.visibilityOfElementLocated(workspaceTab))

  def isCreateThreadButtonDisplayed: Boolean =
    driver.findElements(createThreadButtonLocator).asScala.nonEmpty &&
      getCreateThreadButton.isDisplayed

  def isCreateThreadButtonEnabled: Boolean =
    getCreateThreadButton.isEnabled

  def getThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

  def isIntroTextDisplayedBeforeButton: Boolean = {
    val introLocation  = wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getLocation
    val buttonLocation = getCreateThreadButton.getLocation
    introLocation.getY < buttonLocation.getY
  }
}
