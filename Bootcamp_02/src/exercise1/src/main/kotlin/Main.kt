import com.google.gson.Gson
import java.io.File

data class Vacancy(val profession: String, val level: String, val salary: Int)

data class Company(val name: String, val field_of_activity: String, val vacancies: List<Vacancy>, val contacts: String)

data class CompanyData(val listOfCompanies: List<Company>)

fun main() {
  val gson = Gson()
  val jsonFile = File("/opt/goinfre/perlabru/my-project/kotlin_bootcamp/Bootcamp_02/data-samples/listOfCompanies.json")

  if (!jsonFile.exists()) {
    println("Файл не найден.")
    return
  }

  val jsonData = jsonFile.readText()
  val companyData = gson.fromJson(jsonData, CompanyData::class.java)

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


enum class Activity(val type: String){
  IT("IT"),BANK("Banking"), PUBLIC(" Public services")
}

enum class Profession (val type: String){
  DEV("Developer"), QA("QA"),PM("Project Manager"), ANALYST("Analyst"), DESIGN("Designer")
}

enum class ProfessionLevel(val type: String){
  JUN("Junior"),MID("Middle"),SENIOR("Senior")
}

enum class SalaryLevel(val type: String){
  LOW("< 100000"), MID("100000 - 150000"), HIGH("> 150000")
}