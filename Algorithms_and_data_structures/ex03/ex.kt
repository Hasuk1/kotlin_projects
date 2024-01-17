fun main() {
  val n = readLine()!!.toInt()
  val heights = readLine()!!.split(" ").map(String::toInt)

  val result = findMagistralLevel(n, heights)
  println("$result")
}



fun findMagistralLevel(n: Int, heights: List<Int>): Double {
  val maxLevel = heights.maxOrNull() ?: 0
  val minLevel = heights.minOrNull() ?: 0

  val answer = (maxLevel + minLevel) / 2.0
  return answer
}
