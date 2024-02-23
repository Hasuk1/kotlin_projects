package exercise1.data

import exercise1.enums.Activity
import exercise1.enums.Profession
import exercise1.enums.ProfessionLevel
import exercise1.enums.SalaryLevel

data class Filter(
  val activity: Activity? = null,
  val profession: Profession? = null,
  val professionLevel: ProfessionLevel? = null,
  val salaryLevel: SalaryLevel? = null
)