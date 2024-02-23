package enums

enum class SalaryLevel(val type: String, val range: IntRange) {
  LOW("< 100000", 0 until 100000),
  MID("100000 - 150000", 100000..150000),
  HIGH("> 150000", 150001..Int.MAX_VALUE),
  ALL("All", 0..Int.MAX_VALUE)
}