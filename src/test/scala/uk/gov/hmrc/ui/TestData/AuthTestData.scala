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
    ("pid-cb-001", "Jane", "Smith", "jane.smith@hmrc.gov.uk", "sdec_child_benefits"),
    ("pid-pen-001", "Phil", "Marty", "phil.marty@hmrc.gov.uk", "sdec_pensions"),
    ("pid-both-001", "Sam", "Doe", "sam.doe@hmrc.gov.uk", "sdec_child_benefits,sdec_pensions"),
    ("pid-norole-001", "Alex", "Brown", "alex.brown@hmrc.gov.uk", ""),
    ("pid-bad-001", "Chris", "Green", "chris.green@hmrc.gov.uk", "tax_nonsense")
  )

  val usersWithChildBenefits = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-cb-001")
  }

  val usersWithPensions = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-pen-001")
  }

  val usersWithBothRoles = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-both-001")
  }

  val usersWithNoRoles = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-norole-001")
  }

  val usersWithTax = credentialsWithPassword.filter { row =>
    val pid = row.productElement(0).toString
    pid.contains("pid-bad-001")
  }

  def getUsersWithRole(roleName: String) =
    credentialsWithPassword.filter { row =>
      val pid = row.productElement(0).toString
      pid.contains(pid)
    }
}
