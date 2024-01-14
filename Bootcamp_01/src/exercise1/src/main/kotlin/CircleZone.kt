import kotlin.math.pow
import kotlin.math.sqrt

class CircleZone(private val center: Pair<Int, Int>, private val radius: Int) :Zone() {
  private val phoneNumber = "89347362826"
  override fun isIncidentInside(incident: Incident): Boolean {
    val distance = sqrt(
      (incident.x - center.first).toDouble().pow(2) + (incident.y - center.second).toDouble().pow(2)
    )
    return distance <= radius
  }

  fun getInfo(): String {
    return """
      The zone info:
        The shape of zone: circle
        Phone number: $phoneNumber
    """.trimIndent()
  }
}