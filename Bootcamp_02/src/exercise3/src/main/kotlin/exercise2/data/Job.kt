package exercise2.data

import java.time.YearMonth

data class Job(
  val date_start: YearMonth,
  val date_end: YearMonth,
  val company_name: String,
  val description: String
)
