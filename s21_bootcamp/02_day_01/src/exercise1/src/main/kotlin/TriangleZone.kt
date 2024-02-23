class TriangleZone(private val points: List<Pair<Int, Int>>) : Zone() {
  private val kPhoneNumber = "84352835724"
  override fun isIncidentInside(incident: Incident?): Boolean {
    return if(incident!=null) {
      val (x, y) = Pair(incident.x.toDouble(), incident.y.toDouble())
      val (x1, y1) = points[0]
      val (x2, y2) = points[1]
      val (x3, y3) = points[2]
      val area = 0.5 * (-y2 * x3 + y1 * (-x2 + x3) + x1 * (y2 - y3) + x2 * y3)
      val s = 1 / (2 * area) * (y1 * x3 - x1 * y3 + (y3 - y1) * x + (x1 - x3) * y)
      val t = 1 / (2 * area) * (x1 * y2 - y1 * x2 + (y1 - y2) * x + (x2 - x1) * y)
      s > 0 && t > 0 && 1 - s - t > 0
    } else false
  }

  override fun getPhoneNumber(): String {
    return kPhoneNumber
  }
}