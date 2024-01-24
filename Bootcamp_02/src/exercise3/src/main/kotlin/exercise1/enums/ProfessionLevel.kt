package exercise1.enums

enum class ProfessionLevel(val type: String, val seniority:IntRange) {
  JUN("Junior", 0 until 3), MID("Middle",3 until 6), SENIOR("Senior",6..100), ALL("All",0..100)
}