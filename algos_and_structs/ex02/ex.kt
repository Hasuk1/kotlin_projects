fun main() {
  val n = readLine()!!.toInt()
  val text = readLine()!!
  val (minLength, maxLength) = findMinMaxLength(n, text)
  println("$minLength $maxLength")
}

fun findMinMaxLength(n: Int, text: String): Pair<Int, Int> {
  val linesLengths = text.split("#").map(String::length)
  return Pair(linesLengths.minOrNull() ?: 0, linesLengths.maxOrNull() ?: 0)
}