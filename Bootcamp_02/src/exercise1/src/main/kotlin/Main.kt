import com.google.gson.Gson
import data.*
import enums.*
import java.io.File

fun main() {
  val filter = scanFilter()
  val gson = Gson()
  val jsonFile = File("${System.getProperty("user.dir")}/../../data-samples/listOfCompanies.json")
  if (!jsonFile.exists()) {
    println("File .json not find.")
    return
  }
  val companyData = gson.fromJson(jsonFile.readText(), CompanyData::class.java)
  filterVacancies(filter, companyData)
}

fun scanFilter(): Filter {
  fun selectActivity(): Activity {
    return try {
      println("Select a field of activity:")
      for ((index, activity_type) in Activity.values().withIndex()) {
        println("${index + 1}. ${activity_type.type}")
      }
      val inputActivity = readLine() ?: throw Exception()
      if (inputActivity.toIntOrNull() !in 1..(Activity.values().size)) throw Exception()
      Activity.values()[inputActivity.toInt() - 1]
    } catch (e: Exception) {
      println("It doesn't look like a correct input. Try again.")
      selectActivity()
    }
  }

  fun selectProfession(activity: Activity): Profession {
    return try {
      println("${activity.type}. Select a profession:")
      for ((index, profession_type) in Profession.values().withIndex()) {
        println("${index + 1}. ${profession_type.type}")
      }
      val inputProfession = readLine() ?: throw Exception()
      if (inputProfession.toIntOrNull() !in 1..(Profession.values().size)) throw Exception()
      Profession.values()[inputProfession.toInt() - 1]
    } catch (e: Exception) {
      println("It doesn't look like a correct input. Try again.")
      selectProfession(activity)
    }
  }

  fun selectProfessionLevel(activity: Activity, profession: Profession): ProfessionLevel {
    return try {
      println("${activity.type}. ${profession.type}. Select the level of a candidate:")
      for ((index, professionLevelType) in ProfessionLevel.values().withIndex()) {
        println("${index + 1}. ${professionLevelType.type}")
      }
      val inputProfessionLevel = readLine() ?: throw Exception()
      if (inputProfessionLevel.toIntOrNull() !in 1..(ProfessionLevel.values().size)) throw Exception()
      ProfessionLevel.values()[inputProfessionLevel.toInt() - 1]
    } catch (e: Exception) {
      println("It doesn't look like a correct input. Try again.")
      selectProfessionLevel(activity, profession)
    }
  }

  fun selectSalaryLevel(
    activity: Activity,
    profession: Profession,
    professionLevel: ProfessionLevel
  ): SalaryLevel {
    return try {
      println("${activity.type}. ${profession.type}. ${professionLevel.type}. Select a salary level:")
      for ((index, salaryLevelType) in SalaryLevel.values().withIndex()) {
        println("${index + 1}. ${salaryLevelType.type}")
      }
      val inputSalaryLevel = readLine() ?: throw Exception("")
      if (inputSalaryLevel.toIntOrNull() !in 1..(SalaryLevel.values().size)) throw Exception("")
      SalaryLevel.values()[inputSalaryLevel.toInt() - 1]
    } catch (e: Exception) {
      println("It doesn't look like a correct input. Try again.")
      selectSalaryLevel(activity, profession, professionLevel)
    }
  }

  val activity = selectActivity()
  val profession = selectProfession(activity)
  val professionLevel = selectProfessionLevel(activity, profession)
  val salaryLevel = selectSalaryLevel(activity, profession, professionLevel)
  println("${activity.type}. ${profession.type}. ${professionLevel.type}. ${salaryLevel.type}")
  return Filter(activity, profession, professionLevel, salaryLevel)
}

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

  fun String.professionMask(): String {
    return when (this) {
      "developer" -> "Developer"
      "qa" -> "QA-engineer"
      "pm" -> "Project-manager"
      "analyst" -> "Analyst"
      "designer" -> "Designer"
      else -> this
    }
  }

  fun printVacancyInfo(str1: String, str2: String, str3: Int) {
    var string = "${str1.goPascal()} $str2"
    for (i in 6..31 - string.length) string += " "
    println("$string---    $str3")
  }

  var index = 1
  println("The list of suitable vacancies:")
  companyData.listOfCompanies
    .filter { isAppropriateActivity(it, filter) }
    .forEach { company ->
      company.vacancies
        .filter { isAppropriateVacancy(it, filter) }
        .forEach { vacancy ->
          println("\n${index++}.")
          printVacancyInfo(vacancy.level, vacancy.profession.professionMask(), vacancy.salary)
          println("  ${company.name}")
          println("  ${company.field_of_activity.goPascal()}")
          println("----------------------------------------")
        }
    }
}
