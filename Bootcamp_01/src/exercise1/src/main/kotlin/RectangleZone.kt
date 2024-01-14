import kotlin.math.max
import kotlin.math.min

class RectangleZone(phoneNumber: String, private val points: List<Pair<Int, Int>>) : Zone() {
  override fun isIncidentInside(incident: Incident): Boolean {
    val (x, y) = Pair(incident.x.toDouble(), incident.y.toDouble())
    val (x1, y1) = points[0]
    val (x2, y2) = points[1]
    return x > min(x1, x2) && x < max(x1, x2) && y > min(y1, y2) && y < max(y1, y2)
  }
}