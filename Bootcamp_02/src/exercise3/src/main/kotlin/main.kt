import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data.CompanyData
import data.Filter
import exercise2.data.Job
import exercise2.data.Resume
import enums.Activity
import enums.Profession
import exercise1.enums.ProfessionLevel
import enums.SalaryLevel
import java.io.File
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

fun main() {
  val jsonFileVacancy = File("${System.getProperty("user.dir")}/../files/listOfCompanies.json")
  val jsonFileResume = File("${System.getProperty("user.dir")}/../files/resume.json")
  if (!jsonFileVacancy.exists() || !jsonFileResume.exists()) {
    println("File .json not find.")
    return
  }
  val gsonVacancy = Gson()
  val gsonResume = GsonBuilder()
    .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
    .registerTypeAdapter(YearMonth::class.java, JobDateAdapter())
    .create()
  val vacancy = gsonVacancy.fromJson(jsonFileVacancy.readText(),CompanyData::class.java)
  val resume = gsonResume.fromJson(jsonFileResume.readText(), Resume::class.java)
  val filter = getFiltersFromResume(resume)
//  printCandidateInfo()
  println("The candidate:")
  println("Name: ${resume.candidate_info.name}")
  println("Profession ${resume.candidate_info.profession.professionMask()}")
  println("Experience: ${calculateSeniority(resume.job_experience)} months")

  filterVacancies(filter, vacancy)
}

fun getFiltersFromResume(resume: Resume): Filter {
  fun getProfession(): Profession {
    return when (resume.candidate_info.profession.uppercase()) {
      "DEVELOPER" -> Profession.DEV
      "QA" -> Profession.QA
      "PROJECT MANAGER" -> Profession.PM
      "ANALYST" -> Profession.ANALYST
      "DESIGNER" -> Profession.DESIGN
      else -> Profession.ALL
    }
  }

  fun getProfessionLevel(): ProfessionLevel {
    return when (calculateSeniority(resume.job_experience)) {
      in ProfessionLevel.JUN.seniority.fromMonthToYear() -> ProfessionLevel.JUN
      in ProfessionLevel.MID.seniority.fromMonthToYear() -> ProfessionLevel.MID
      in ProfessionLevel.SENIOR.seniority.fromMonthToYear() -> ProfessionLevel.SENIOR
      else -> throw Exception("Incorrect JSON")
    }
  }
  return Filter(Activity.ALL, getProfession(), getProfessionLevel(), SalaryLevel.ALL)
}

fun calculateSeniority(jobs: List<Job>): Long {
  var totalMonths = 0L
  for (job in jobs) {
    val startDate = job.date_start.atDay(1)
    val endDate = job.date_end.plusMonths(1).atDay(1) // Add one month to include the end month
    totalMonths += ChronoUnit.MONTHS.between(startDate, endDate)
  }
  return totalMonths
}

fun IntRange.fromMonthToYear(): IntRange {
  return this.first * 12..this.last * 12
}