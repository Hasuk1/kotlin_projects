import kotlin.math.pow
import kotlin.math.sqrt

class CircleZone(private val center: Pair<Int, Int>, private val radius: Int) :Zone() {
  private val kPhoneNumber = "89347362826"
  override fun isIncidentInside(incident: Incident?): Boolean {
    return if (incident!=null) {
      val distance = sqrt(
        (incident.x - center.first).toDouble().pow(2) + (incident.y - center.second).toDouble()
          .pow(2)
      )
      distance < radius
    } else false
  }

  override fun getPhoneNumber(): String {
    return kPhoneNumber
  }
}