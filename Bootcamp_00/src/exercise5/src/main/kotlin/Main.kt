import kotlin.math.*

fun main() {
  val x1 = readCoordinate("Input x1:")
  val y1 = readCoordinate("Input y1:")
  val r1 = readRadius("Input r1:")
  val x2 = readCoordinate("Input x2:")
  val y2 = readCoordinate("Input y2:")
  val r2 = readRadius("Input r2:")
  val distance = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))

  when {
    distance < r1 - r2 -> println("Result: circle 2 is inside circle 1")
    distance < r2 - r1 -> println("Result: circle 1 is inside circle 2")
    distance <= r1 + r2 -> {
      if (distance < r1 + r2) {
        try {
          val intersectionPoints = calculateIntersectionPoints(x1, y1, r1, x2, y2, r2)
          println("Result: the circles intersect")
          intersectionPoints.forEach { point ->
            println("Intersection point: (${"%.6f".format(point.first)}, ${"%.6f".format(point.second)})")
          }
        } catch (e: IllegalArgumentException) {
          println("Circles do not touch or intersect.")
        }
      } else {
        println("Result: the circles touch each other")
        val touchPoint = calculateTouchPoint(x1, y1, r1, x2, y2)
        println("Touch point: (${"%.6f".format(touchPoint.first)}, ${"%.6f".format(touchPoint.second)})")

      }
    }

    distance > r1 + r2 -> println("Result: the circles do not intersect")
    else -> println("Result: unexpected case")
  }
}

fun calculateIntersectionPoints(
  x1: Double, y1: Double, r1: Double,
  x2: Double, y2: Double, r2: Double
): List<Pair<Double, Double>> {
  val d = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
  if (d == 0.0 && r1 == r2) throw IllegalArgumentException("Infinite touch points for coincident circles.")
  val alpha = atan2(y2 - y1, x2 - x1)
  val beta = acos((r1.pow(2) + d.pow(2) - r2.pow(2)) / (2 * r1 * d))

  val xA = x1 + r1 * cos(alpha - beta)
  val yA = y1 + r1 * sin(alpha - beta)

  val xB = x1 + r1 * cos(alpha + beta)
  val yB = y1 + r1 * sin(alpha + beta)

  return listOf(xA to yA, xB to yB)
}

fun calculateTouchPoint(
  x1: Double, y1: Double, r1: Double,
  x2: Double, y2: Double
): Pair<Double, Double> {
  val d = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
  val touchX = x1 + (r1 * (x2 - x1)) / d
  val touchY = y1 + (r1 * (y2 - y1)) / d
  return touchX to touchY
}
