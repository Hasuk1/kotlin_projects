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
  val filter = scanFilter()
  val gson = Gson()
  val jsonFile = File("${System.getProperty("user.dir")}/../../data-samples/listOfCompanies.json")
  if (!jsonFile.exists()) {
    println("File.json not find.")
    return
  }

  val companyData = gson.fromJson(jsonFile.readText(), CompanyData::class.java)

  // Теперь у вас есть коллекция компаний, и вы можете взаимодействовать с ней
  for (company in companyData.listOfCompanies) {
    println("Компания: ${company.name}, Сфера деятельности: ${company.field_of_activity}, Контакты: ${company.contacts}")
    for (vacancy in company.vacancies) {
      println("  Вакансия: ${vacancy.profession}, Уровень: ${vacancy.level}, Зарплата: ${vacancy.salary}")
    }
    println("-------------")
  }

  // Продолжайте с вашим существующим кодом, добавляя логику взаимодействия с пользователем и фильтрации вакансий
}


enum class Activity(val type: String) {
  IT("IT"), BANK("Banking"), PUBLIC(" Public services")
}

enum class Profession(val type: String) {
  DEV("Developer"), QA("QA"), PM("Project Manager"), ANALYST("Analyst"), DESIGN("Designer")
}

enum class ProfessionLevel(val type: String) {
  JUN("Junior"), MID("Middle"), SENIOR("Senior")
}

enum class SalaryLevel(val type: String) {
  LOW("< 100000"), MID("100000 - 150000"), HIGH("> 150000")
}

data class Filter(
  var activity: Activity? = null,
  val profession: Profession? = null,
  val professionLevel: ProfessionLevel? = null,
  val salaryLevel: SalaryLevel? = null
)

fun scanFilter(): Filter {
  val filter = Filter()

  // Ввод сферы деятельности
  println("Select a field of activity:")
  for ((index, activity) in Activity.values().withIndex()) {
    println("${index + 1}. ${activity.type}")
  }
  println("${Activity.values().size + 1}. All")

  var inputActivity: String?
  do {
    inputActivity = readLine()
    if (inputActivity == null || inputActivity.toIntOrNull() !in 1..(Activity.values().size + 1)) {
      println("Это не похоже на правильный ввод.")
    }
  } while (inputActivity == null || inputActivity.toIntOrNull() !in 1..(Activity.values().size + 1))

  filter.activity = if (inputActivity.toInt() <= Activity.values().size) {
    Activity.values()[inputActivity.toInt() - 1]
  } else {
    null
  }

  // Ввод профессии
  // ...

  // Ввод уровня кандидата
  // ...

  // Ввод уровня зарплаты
  // ...

  return filter
}

//fun getFilteredVacancy(filter: Filter, companyData:CompanyData):CompanyData{
//
//}