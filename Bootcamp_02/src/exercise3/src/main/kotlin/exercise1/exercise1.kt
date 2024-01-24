import data.Company
import data.CompanyData
import data.Filter
import data.Vacancy
import enums.Activity
import enums.Profession
import exercise1.enums.ProfessionLevel

fun filterVacancies(filter: Filter, companyData: CompanyData) {
  fun isAppropriateActivity(company: Company, filter: Filter): Boolean {
    return company.field_of_activity.uppercase() == filter.activity!!.type.uppercase() || filter.activity == Activity.ALL
  }

  fun isAppropriateVacancy(vacancy: Vacancy, filter: Filter): Boolean {
    return (vacancy.profession.uppercase() == filter.profession!!.type.uppercase() || filter.profession == Profession.ALL) &&
            (vacancy.level.uppercase() == filter.professionLevel!!.type.uppercase() || filter.professionLevel == ProfessionLevel.ALL) &&
            (vacancy.salary in filter.salaryLevel!!.range)
  }

  fun String.goPascal(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
  }



  fun printVacancyInfo(str1: String, str2: String, str3: Int) {
    var string = "${str1.goPascal()} $str2"
    for (i in 6..31 - string.length) string += " "
    println("$string---    $str3")
  }

  var index = 1
  println("\nSuitable vacancies:")
  companyData.listOfCompanies
    .filter { isAppropriateActivity(it, filter) }
    .forEach { company ->
      company.vacancies
        .filter { isAppropriateVacancy(it, filter) }
        .forEach { vacancy ->
          println("\n${index++}.")
          println(company.name)
          println("Field of activity: ${company.field_of_activity}")
          println("  Candidate level: ${vacancy.level.goPascal()}")
          println("  Salary: ${vacancy.salary}")
          println("  Contacts: ${company.contacts}")
        }
    }
}

fun String.professionMask(): String {
  return when (this.uppercase()) {
    "DEVELOPER" -> "Developer"
    "QA" -> "QA-engineer"
    "PM" -> "Project-manager"
    "ANALYST" -> "Analyst"
    "DESIGNER" -> "Designer"
    else -> this
  }
}