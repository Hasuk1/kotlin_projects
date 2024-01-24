package data

import java.time.LocalDate

data class CandidateInfo(
  val name: String,
  val profession: String,
  val sex: String,
  val birth_date: LocalDate,
  val contacts: Contacts,
  val relocation: String
)
