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
import org.openqa.selenium.{By, WebElement}
import uk.gov.hmrc.ui.pages.AuthLoginPage.driver
import uk.gov.hmrc.ui.pages.WorkspacePage

import java.time.Duration

object WorkspacePage extends BasePage {

  val workspacePageHeading:        By = By.cssSelector("#main-content h2.govuk-heading-m")
  val createThreadButtonLocator:   By = By.cssSelector("a.govuk-button[href*='create-thread']")
  val firstThreadReferenceLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child th.govuk-table__header a.govuk-link")
  val firstRelatedReferenceLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child td.govuk-table__cell:nth-child(2)")
  val firstExternalContactLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child td.govuk-table__cell:nth-child(3) a.govuk-link")
  val firstStatusLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child td.govuk-table__cell:nth-child(4)")
  val firstWaitingOnLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child td.govuk-table__cell:nth-child(5)")
  val firstDeadlineLocator: By =
    By.cssSelector("table.govuk-table tbody tr:first-child td.govuk-table__cell:nth-child(6)")
  val specificThreadReferenceValue: By =
    By.cssSelector(
      "dl.govuk-summary-list dd.govuk-summary-list__value:nth-of-type(1)"
    )
  val specificRelatedReferenceValue: By =
    By.cssSelector(
      "div.govuk-summary-list__row:nth-child(2) dd.govuk-summary-list__value"
    )
  val specificExternalContactValue: By =
    By.cssSelector(
      "div.govuk-summary-list__row:nth-child(3) dd.govuk-summary-list__value"
    )
  val specificStatusValue: By =
    By.cssSelector(
      "div.govuk-summary-list__row:nth-child(4) dd.govuk-summary-list__value"
    )
  val specificWaitingOnValue: By =
    By.cssSelector(
      "div.govuk-summary-list__row:nth-child(5) dd.govuk-summary-list__value"
    )
  val threadReferenceLocator:  By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(1)")
  val relatedReferenceLocator: By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(2)")
  val externalContactLocator:  By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(3)")
  val statusLocator:           By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(4)")
  val waitingOnLocator:        By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(5)")
  val deadlineLocator:         By = By.cssSelector("table.govuk-table thead th.govuk-table__header:nth-child(6)")
  val statusValueText:         By = By.cssSelector(
    "#main-content > div > div.table-scroll-wrapper > table > tbody > tr:nth-child(1) > td.govuk-table__cell.sdec-priority-column > strong"
  )
  val statusValueWaitingText: By = By.cssSelector("table.govuk-table tbody tr td.govuk-table__cell:nth-child(4)")

  def getWorkspaceHeadingText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(workspacePageHeading)).getText.trim

  def getCreateThreadButton: WebElement =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getThreadButtonText: String =
    getCreateThreadButton.getText.trim

  def selectCreateThreadButton(): Unit =
    getCreateThreadButton.click()

  def getStatusValueText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusValueText)).getText.trim

  def getThreadReferenceHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(threadReferenceLocator)).getText.trim

  def getRelatedReferenceHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(relatedReferenceLocator)).getText.trim

  def getExternalContactHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(externalContactLocator)).getText.trim

  def getStatusHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusLocator)).getText.trim

  def getWaitingOnHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(waitingOnLocator)).getText.trim

  def getDeadlineHeader: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(deadlineLocator)).getText.trim

  def getStatusValueWaitingText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(statusValueWaitingText)).getText.trim

  def getFirstThreadReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstThreadReferenceLocator)).getText.trim

  def getFirstRelatedReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstRelatedReferenceLocator)).getText.trim

  def getFirstExternalContactText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstExternalContactLocator)).getText.trim

  def getFirstStatusText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstStatusLocator)).getText.trim

  def getFirstWaitingOnText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstWaitingOnLocator)).getText.trim

  def getFirstDeadlineText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(firstDeadlineLocator)).getText.trim

  def getSpecificThreadReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(specificThreadReferenceValue)).getText.trim

  def getSpecificRelatedReferenceText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(specificRelatedReferenceValue)).getText.trim

  def getSpecificExternalContactText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(specificExternalContactValue)).getText.trim

  def getSpecificStatusText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(specificStatusValue)).getText.trim

  def getSpecificWaitingOnText: String =
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(specificWaitingOnValue)).getText.trim

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

    threadDetails
  }

}
