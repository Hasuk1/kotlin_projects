import com.google.gson.Gson
import java.io.File

data class Vacancy(val profession: String, val level: String, val salary: Int)

data class Company(
  val name: String,
  val field_of_activity: String,
  val vacancies: List<Vacancy>,
  val contacts: String
)

data class CompanyData(val listOfCompanies: List<Company>)

fun main() {
  val filter = scanFilter() ?: return
  val gson = Gson()
  val jsonFile = File("${System.getProperty("user.dir")}/../../data-samples/listOfCompanies.json")
  if (!jsonFile.exists()) {
    println("File .json not find.")
    return
  }
  val companyData = gson.fromJson(jsonFile.readText(), CompanyData::class.java)
  val filteredVacancy = filterVacancies(filter, companyData)
//  for (company in companyData.listOfCompanies) {
//    println("Компания: ${company.name}, Сфера деятельности: ${company.field_of_activity}, Контакты: ${company.contacts}")
//    for (vacancy in company.vacancies) {
//      println("  Вакансия: ${vacancy.profession}, Уровень: ${vacancy.level}, Зарплата: ${vacancy.salary}")
//    }
//    println("-------------")
//  }

}


enum class Activity(val type: String) {
  IT("IT"), BANK("Banking"), PUBLIC("Public services"), ALL("All")
}

enum class Profession(val type: String) {
  DEV("Developer"), QA("QA"), PM("Project Manager"), ANALYST("Analyst"), DESIGN("Designer"), ALL("All")
}

enum class ProfessionLevel(val type: String) {
  JUN("Junior"), MID("Middle"), SENIOR("Senior"), ALL("All")
}

enum class SalaryLevel(val type: String, val range: IntRange) {
  LOW("< 100000", 0 until 100000),
  MID("100000 - 150000", 100000..150000),
  HIGH("> 150000", 150001..Int.MAX_VALUE),
  ALL("All", 0..Int.MAX_VALUE)
}

data class Filter(
  val activity: Activity? = null,
  val profession: Profession? = null,
  val professionLevel: ProfessionLevel? = null,
  val salaryLevel: SalaryLevel? = null
)

fun scanFilter(): Filter? {
  return try {
    println("Select a field of activity:")
    for ((index, activity_type) in Activity.values().withIndex()) {
      println("${index + 1}. ${activity_type.type}")
    }
    val inputActivity = readLine() ?: throw Exception("Incorrect activity input")
    if (inputActivity.toIntOrNull() !in 1..(Activity.values().size)) throw Exception("Incorrect activity input")
    val activity = Activity.values()[inputActivity.toInt() - 1]

    println("${activity.type}. Select a profession:")
    for ((index, profession_type) in Profession.values().withIndex()) {
      println("${index + 1}. ${profession_type.type}")
    }
    val inputProfession = readLine() ?: throw Exception("Incorrect profession input")
    if (inputProfession.toIntOrNull() !in 1..(Profession.values().size)) throw Exception("Incorrect profession input")
    val profession = Profession.values()[inputProfession.toInt() - 1]

    println("${activity.type}. ${profession.type}. Select the level of a candidate:")
    for ((index, professionLevelType) in ProfessionLevel.values().withIndex()) {
      println("${index + 1}. ${professionLevelType.type}")
    }
    val inputProfessionLevel = readLine() ?: throw Exception("Incorrect profession level input")
    if (inputProfessionLevel.toIntOrNull() !in 1..(ProfessionLevel.values().size)) throw Exception("Incorrect profession level input")
    val professionLevel = ProfessionLevel.values()[inputProfessionLevel.toInt() - 1]

    println("${activity.type}. ${profession.type}. ${professionLevel.type}. Select a salary level:")
    for ((index, salaryLevelType) in SalaryLevel.values().withIndex()) {
      println("${index + 1}. ${salaryLevelType.type}")
    }
    val inputSalaryLevel = readLine() ?: throw Exception("Incorrect salary level input")
    if (inputSalaryLevel.toIntOrNull() !in 1..(SalaryLevel.values().size)) throw Exception("Incorrect salary level input")
    val salaryLevel = SalaryLevel.values()[inputSalaryLevel.toInt() - 1]

    println("${activity.type}. ${profession.type}. ${professionLevel.type}. ${salaryLevel.type}")
    Filter(activity, profession, professionLevel, salaryLevel)
  } catch (e: Exception) {
    println("Error: ${e.message}")
    null
  }
}


fun filterVacancies(filter: Filter, companyData: CompanyData) {
  for (company in companyData.listOfCompanies) {
    if (company.field_of_activity.uppercase() == filter.activity!!.type.uppercase() || filter.activity == Activity.ALL) {
      for (vacancy in company.vacancies) {
        if ((vacancy.profession.uppercase() == filter.profession!!.type.uppercase() || filter.profession == Profession.ALL) &&
          (vacancy.level.uppercase() == filter.professionLevel!!.type.uppercase() || filter.professionLevel == ProfessionLevel.ALL) &&
          (vacancy.salary in filter.salaryLevel!!.range || filter.salaryLevel == SalaryLevel.ALL)
        ) {
          println("\n${vacancy.level.goPascal()} ${vacancy.profession.professionMask()}     ---      ${vacancy.salary}")
          println("  ${company.name}")
          println("  ${company.field_of_activity.goPascal()}")
          println("---------------------------------------")
        }
      }
    }
  }
}

fun String.goPascal():String {
  return this.substring(0,1).uppercase()+this.substring(1)
}

fun String.professionMask():String{
  return when (this){
    "developer" -> "Developer"
    "qa" -> "QA-engineer"
    "pm" -> "Project Manager"
    "analyst" -> "Analyst"
    "designer" -> "Designer"
    else -> this
  }
}