import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import data.Resume
import java.io.File
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun main() {
  fun printResume(resume: Resume) {
    println("Block 1")
    println(resume.candidate_info)
    println("...\n")
    println("Block 2")
    println(resume.education)
    println("...\n")
    println("Block 3")
    println(resume.job_experience)
    println("...\n")
    println("Block 4")
    println(resume.free_form)
    println("...")
  }

  val gson = GsonBuilder()
    .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
    .registerTypeAdapter(YearMonth::class.java, JobExperienceAdapter())
    .create()
  val jsonFile = File("${System.getProperty("user.dir")}/../files/resume.json")
  if (!jsonFile.exists()) {
    println("File .json not find.")
    return
  }
  printResume(gson.fromJson(jsonFile.readText(), Resume::class.java))
}

class LocalDateAdapter : JsonDeserializer<LocalDate> {
  override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
    return LocalDate.parse(json?.asString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
  }
}

class JobExperienceAdapter : JsonDeserializer<YearMonth> {
  override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): YearMonth {
    return YearMonth.parse(json?.asString, DateTimeFormatter.ofPattern("MM.yyyy"))
  }
}
