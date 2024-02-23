import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class LocalDateAdapter : JsonDeserializer<LocalDate> {
  override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDate {
    return LocalDate.parse(json?.asString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
  }
}

class JobDateAdapter : JsonDeserializer<YearMonth> {
  override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): YearMonth {
    return YearMonth.parse(json?.asString, DateTimeFormatter.ofPattern("MM.yyyy"))
  }
}
