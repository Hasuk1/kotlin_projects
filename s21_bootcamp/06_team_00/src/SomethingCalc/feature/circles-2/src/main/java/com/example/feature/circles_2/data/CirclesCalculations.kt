package com.example.feature.circles_2.data

import com.example.core.logger.Logger
import kotlin.math.acos
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun getCirclesStatus(circle1: Circle, circle2: Circle): String {

  val distance = sqrt((circle2.x - circle1.x).pow(2) + (circle2.y - circle1.y).pow(2))

  return when {
    distance < circle1.r - circle2.r -> {
      Logger.info("Result: circle 2 is inside circle 1 | Module: Circles-2")
      "Result: circle 2 is inside circle 1"
    }

    distance < circle2.r - circle1.r -> {
      Logger.info("Result: circle 1 is inside circle 2 | Module: Circles-2")
      "Result: circle 1 is inside circle 2"
    }

    distance <= circle1.r + circle2.r -> {
      if (distance < circle1.r + circle2.r) {
        try {
          var intersectionPointsInfo = "Result\nThe circles intersect."
          val intersectionPoints = calculateIntersectionPoints(circle1, circle2)
          intersectionPoints.forEachIndexed { index, point ->
            intersectionPointsInfo += "\n\nIntersection point ${index + 1}:\n(${"%.6f".format(point.first)}, ${
              "%.6f".format(
                point.second
              )
            })"
          }
          intersectionPointsInfo
        } catch (e: IllegalArgumentException) {
          Logger.info("Circles do not touch or intersect. | Module: Circles-2")
          "Circles do not touch or intersect."
        }
      } else {
        try {
          val touchPoint = calculateTouchPoint(circle1, circle2)
          "Result\nThe circles touch each other.\nTouch point:\n(${"%.6f".format(touchPoint.first)}, ${
            "%.6f".format(
              touchPoint.second
            )
          })"
        } catch (e: IllegalArgumentException) {
          Logger.info("Circles do not touch or intersect. | Module: Circles-2")
          "Circles do not touch or intersect."
        }
      }
    }

    distance > circle1.r + circle2.r -> "Result: the circles do not intersect"
    else -> {
      Logger.warning("Unexpected case in calculate circles info | Module: Circles-2")
      "Result: unexpected case"
    }
  }
}

fun calculateIntersectionPoints(circle1: Circle, circle2: Circle): List<Pair<Double, Double>> {
  val d = sqrt((circle2.x - circle1.x).pow(2) + (circle2.y - circle1.y).pow(2))
  if (d == 0.0 && circle1.r == circle2.r) throw IllegalArgumentException("Infinite touch points for coincident circles.")
  val alpha = atan2(circle2.y - circle1.y, circle2.x - circle1.x)
  val beta = acos((circle1.r.pow(2) + d.pow(2) - circle2.r.pow(2)) / (2 * circle1.r * d))
  val xA = circle1.x + circle1.r * cos(alpha - beta)
  val yA = circle1.y + circle1.r * sin(alpha - beta)
  if (xA.isNaN() || yA.isNaN()) {
    Logger.error("Intersection point calculation resulted in NaN. | Module: Circles-2")
    throw IllegalArgumentException("Intersection point calculation resulted in NaN.")
  }
  val xB = circle1.x + circle1.r * cos(alpha + beta)
  val yB = circle1.y + circle1.r * sin(alpha + beta)
  if (xB.isNaN() || yB.isNaN()) {
    Logger.error("Intersection point calculation resulted in NaN. | Module: Circles-2")
    throw IllegalArgumentException("Intersection point calculation resulted in NaN.")
  }
  return listOf(xA to yA, xB to yB)
}

fun calculateTouchPoint(circle1: Circle, circle2: Circle): Pair<Double, Double> {
  val d = sqrt((circle2.x - circle1.x).pow(2) + (circle2.y - circle1.y).pow(2))
  val touchX = circle1.x + (circle1.r * (circle2.x - circle1.x)) / d
  val touchY = circle1.y + (circle1.r * (circle2.y - circle1.y)) / d
  if (touchX.isNaN() || touchY.isNaN()) {
    Logger.error("Touch point calculation resulted in NaN. | Module: Circles-2")
    throw IllegalArgumentException("Touch point calculation resulted in NaN.")
  }
  return touchX to touchY
}

