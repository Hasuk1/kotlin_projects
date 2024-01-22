import kotlin.math.pow

fun main() {
  println(calculateTotalPoints(readInput()))
}

fun readInput(): List<Pair<Double, Double>> {
  return List(3) {
    val (x, y) = readLine()!!.split(" ").map(String::toDouble)
    x to y
  }
}

fun calculatePoints(x: Double, y: Double): Int {
  val distance = (x * x + y * y).pow(0.5)
  return when {
    distance <= 0.1 -> 3
    distance <= 0.8 -> 2
    distance <= 1.0 -> 1
    else -> 0
  }
}

fun calculateTotalPoints(input: List<Pair<Double, Double>>): Int {
  return input.sumOf { (x, y) -> calculatePoints(x, y) }
}
