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

package uk.gov.hmrc.ui.TestData

import org.scalatest.prop.Tables.Table

object AuthTestData {

  val credentialsWithPassword = Table(
    ("pid", "givenName", "surName", "email", "roles"),
    ("1001", "John", "Test", "name@example.com", "SDEC_Child_Benefit_Manager"), // no my threads button
    ("123456", "VAT Success", "Test User", "name@example.com", "SDEC_VAT_User"), // yes my threads button
    ("1004", "Mary", "Lamb", "name@example.com", "SDEC_Audit_User"), // yes my threads button
    ("1002", "James", "Brown", "name@example.com", "SDEC_Child_Benefit_User"), // no my threads button
    ("1003", "James", "Brown", "name@example.com", "SDEC_VAT_Manager"), // yes my threads button
    ("pid-pen-001", "John", "Smith", "name@example.com", "SDEC_VAT_User") // yes my threads button with filter available
  )

  val usersWithChildBenefitsManagerRole = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("1001")
  }

  val usersWithVATUserRole = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("123456")
  }

  val usersWithAuditRole = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("1004")
  }

  val usersWithChildBenefitsUser = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("1002")
  }

  val usersWithVATManager = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("1003")
  }

  val usersWithMyFilterThread = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-pen-001")
  }

  def getUsersWithRole(roleName: String) =
    credentialsWithPassword.filter { row =>
      val pid = row.productElement(0).toString
      pid.contains(pid)
    }
}
