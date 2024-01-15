class RectangleZone(private val points: List<Pair<Int, Int>>) : Zone() {
  override fun isIncidentInside(incident: Incident?): Boolean {
    return if (incident != null) {
      val x = incident.x.toDouble()
      val y = incident.y.toDouble()

      var inside = false
      var j = points.size - 1
      for (i in 0 until points.size) {
        val xi = points[i].first
        val yi = points[i].second
        val xj = points[j].first
        val yj = points[j].second
        if (((yi > y)!=(yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) inside = !inside
        j = i
      }
      inside
    } else false
  }
}
