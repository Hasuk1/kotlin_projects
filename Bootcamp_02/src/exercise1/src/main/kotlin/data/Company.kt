package data

data class Company(
  val name: String,
  val field_of_activity: String,
  val vacancies: List<Vacancy>,
  val contacts: String
)
