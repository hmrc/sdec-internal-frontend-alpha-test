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
import org.openqa.selenium.{By, WebElement}

object WorkspacePage extends BasePage {

  val heading:                      By = By.xpath("/html/body/header/div/div[2]/a")
  val workspaceTab:                 By = By.xpath("//*[@id=\"navigation\"]/li[2]/a")
  val firstThreadReferenceLocator:  By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/th/a")
  val firstRelatedReferenceLocator: By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/td[1]")
  val firstExternalContactLocator:  By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/td[2]/a")
  val firstStatusLocator:           By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/td[3]")
  val firstWaitingOnLocator:        By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/td[4]")
  val threadReferenceLocator:       By = By.xpath("//*[@id=\"main-content\"]/div/dl/div[1]/dd")
  val relatedReferenceLocator:      By = By.xpath("//*[@id=\"main-content\"]/div/dl/div[2]/dd")
  val externalContactLocator:       By = By.xpath("//*[@id=\"main-content\"]/div/dl/div[3]/dd")
  val statusLocator:                By = By.xpath("//*[@id=\"main-content\"]/div/dl/div[4]/dd")
  val waitingOnLocator:             By = By.xpath("//*[@id=\"main-content\"]/div/dl/div[5]/dd")

  val threadInformationText:         By = By.xpath("//*[@id=\"main-content\"]/div/h2")
  val threadReferenceText:           By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[1]")
  val relatedReferenceText:          By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[2]")
  val externalContactText:           By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[3]")
  val statusText:                    By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[4]")
  val waitingOnText:                 By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[5]")
  val deadlineText:                  By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/thead/tr/th[6]")
  val statusValueText:               By = By.xpath("//*[@id=\"main-content\"]/div/div[2]/table/tbody/tr[1]/td[6]")
  val statusValueNeedsAttentionText: By =
    By.cssSelector("#main-content > div > div.table-scroll-wrapper > table > tbody > tr:nth-child(4) > td:nth-child(4)")

  def getStatusValueText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusValueText)).getText.trim

  def getThreadReferenceValue: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(threadReferenceLocator)).getText.trim

  def getRelatedReferenceValue: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(relatedReferenceLocator)).getText.trim

  def getExternalContactValue: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(externalContactLocator)).getText.trim

  def getStatusValue: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusLocator)).getText.trim

  def getWaitingOnValue: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(waitingOnLocator)).getText.trim

  def getStatusValueNeedsAttentionText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusValueNeedsAttentionText)).getText.trim

  def getThreadInformationText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(threadInformationText)).getText.trim

  def getThreadReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(threadReferenceText)).getText.trim

  def getRelatedReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(relatedReferenceText)).getText.trim

  def getExternalContactText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(externalContactText)).getText.trim

  def getStatusText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusText)).getText.trim

  def getWaitingOnText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(waitingOnText)).getText.trim

  def getDeadlineText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(deadlineText)).getText.trim

  def getHeading: WebElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(heading))

  def getWorkspaceTab: WebElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(workspaceTab))

  def firstThreadReferenceElement: WebElement =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstThreadReferenceLocator))

  def selectFirstThreadReference(): Unit =
    firstThreadReferenceElement.click()

  def getThreadDetails: List[String] = {
    val firstThreadReferenceText: String =
      webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstThreadReferenceLocator)).getText.trim

    val firstRelatedReferenceText: String =
      webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstRelatedReferenceLocator)).getText.trim

    val firstExternalContactText: String =
      webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstExternalContactLocator)).getText.trim

    val firstStatusText: String =
      webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstStatusLocator)).getText.trim

    val firstWaitingOnText: String =
      webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstWaitingOnLocator)).getText.trim

    val threadDetails: List[String] = List(
      firstThreadReferenceText,
      firstRelatedReferenceText,
      firstExternalContactText,
      firstStatusText,
      firstWaitingOnText
    )

    threadDetails.foreach(println)
    threadDetails
  }

}
