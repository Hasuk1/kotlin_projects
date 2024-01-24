package data

import enums.*
import exercise1.enums.ProfessionLevel

data class Filter(
  val activity: Activity? = null,
  val profession: Profession? = null,
  val professionLevel: ProfessionLevel? = null,
  val salaryLevel: SalaryLevel? = null
)