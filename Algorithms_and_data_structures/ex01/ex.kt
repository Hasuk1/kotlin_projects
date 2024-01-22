import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
  println(calculateTotalPoints(readInput()))
}

fun readInput(): List<Pair<Double, Double>> {
  val points = mutableListOf<Pair<Double, Double>>()
  for (i in 1..3) {
    val (x, y) = readLine()!!.split(" ").map { it.toDouble() }
    points.add(x to y)
  }
  return points
}

fun calculatePoints(x: Double, y: Double): Int {
  val distance = sqrt(x.pow(2) + y.pow(2))
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
