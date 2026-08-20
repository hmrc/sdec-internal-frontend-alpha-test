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

import java.time.Duration
import scala.jdk.CollectionConverters.*

object CreateThreadPage extends BasePage {

  val createThreadButtonLocator: By = By.xpath("//*[@id=\"main-content\"]/div/div[1]/a")
  val introTextLocator: By          = By.xpath("//*[contains(normalize-space(), 'Share Files Securely')]")

  private val wait = new WebDriverWait(driver, Duration.ofSeconds(10))

  def getCreateThreadButton: WebElement =
    wait.until(ExpectedConditions.visibilityOfElementLocated(createThreadButtonLocator))

  def getIntroductoryText: String =
    wait.until(ExpectedConditions.visibilityOfElementLocated(introTextLocator)).getText.trim

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
