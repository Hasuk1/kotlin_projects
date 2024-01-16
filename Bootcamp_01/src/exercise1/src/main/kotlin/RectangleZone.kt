import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2

class RectangleZone(private val points: List<Pair<Int, Int>>) : Zone() {
  private val kPhoneNumber = "89857889497"
  override fun isIncidentInside(incident: Incident?): Boolean {
    if (incident==null) return false
    val point = Point(incident.x.toDouble(), incident.y.toDouble())
    var angleSum = 0.0
    for (i in points.indices) {
      val vi = Point(points[i].first.toDouble(), points[i].second.toDouble())
      val vPrev = Point(
        points[(i + points.size - 1) % points.size].first.toDouble(),
        points[(i + points.size - 1) % points.size].second.toDouble()
      )
      angleSum += angleBetweenPoints(point, vPrev, vi)
    }
    return abs(angleSum) > 0.000001
  }

  override fun getPhoneNumber(): String {
    return kPhoneNumber
  }

  private fun angleBetweenPoints(p: Point, a: Point, b: Point): Double {
    val angle1 = atan2(a.y - p.y, a.x - p.x)
    val angle2 = atan2(b.y - p.y, b.x - p.x)
    var angle = angle2 - angle1
    if (angle > PI) {
      angle -= 2 * PI
    } else if (angle < -PI) {
      angle += 2 * PI
    }
    return angle
  }

  data class Point(val x: Double, val y: Double)
}
