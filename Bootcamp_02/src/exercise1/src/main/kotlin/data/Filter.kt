package data

import enums.*

data class Filter(
  val activity: Activity? = null,
  val profession: Profession? = null,
  val professionLevel: ProfessionLevel? = null,
  val salaryLevel: SalaryLevel? = null
)