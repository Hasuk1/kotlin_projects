import kotlin.math.*

fun readZone(): Zone? {
  println("Enter zone parameters:")
  val input = readLine() ?: return null
  val parts = input.split(" ")

  return try {
    when (parts.size) {
      2 -> parts[0].split(";")
        .let { (x, y) -> CircleZone(Pair(x.toInt(), y.toInt()), parts[1].toInt()) }

      3 -> {
        TriangleZone(
          listOf(parts[0].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) },
            parts[1].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) },
            parts[2].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) })
        )
      }

      4 -> {
        RectangleZone(
          listOf(parts[0].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) },
            parts[1].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) },
            parts[2].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) },
            parts[3].split(";").let { (x, y) -> Pair(x.toInt(), y.toInt()) })
        )
      }

      else -> null
    }
  } catch (e: Exception) {
    println("Invalid input. Please enter valid zone parameters.")
    null
  }
}

fun readIncident(): Incident? {
  val phones = arrayOf(
    "89583278394",
    "+74829573828",
    null,
    "+7-938-284-35-66",
    "74831743929",
    null,
    "89453337248",
    "+74924753932",
    null,
    null
  )
  val descriptions = arrayOf(
    "My cat can't get off the tree",
    "I can't find my TV remote",
    "There is an UFO kidnapping a cow",
    "There is a bug in compiler, help me please!",
    "two number 9's, a number 9 large, a number 6 with extra dip..."
  )

  fun getIncidentType(description: String): IncidentType? {
    return when {
      "FIRE" in description.uppercase() -> IncidentType.FIRE
      "GAS" in description.uppercase() && "LEAK" in description.uppercase() -> IncidentType.GAS_LEAK
      "CAT" in description.uppercase() && "TREE" in description.uppercase() -> IncidentType.CAT_ON_TREE
      else -> null
    }
  }
  return try {
    println("\nEnter an incident coordinates:")
    val input = readLine() ?: return null
    val coordinates = input.split(";")
    val description = descriptions.random()
    val phone = phones.random()
    val type = getIncidentType(description)
    Incident(coordinates[0].toInt(), coordinates[1].toInt(), description, phone, type)
  } catch (e: Exception) {
    println("Invalid input. Please enter valid incident coordinates.")
    null
  }
}

data class Incident(
  val x: Int, val y: Int, val description: String, val phoneNumber: String?, val type: IncidentType?
)

enum class IncidentType(val type: String) {
  FIRE("Fire"), GAS_LEAK("Gas leak"), CAT_ON_TREE("Cat on the tree")
}

open class Zone() {
  private val kPhoneNumber = "88008473824"
  open fun isIncidentInside(incident: Incident?): Boolean {
    return false
  }

  open fun getPhoneNumber(): String {
    return kPhoneNumber
  }
}

class CircleZone(private val center: Pair<Int, Int>, private val radius: Int) : Zone() {
  private val kPhoneNumber = "89347362826"
  override fun isIncidentInside(incident: Incident?): Boolean {
    return if (incident!=null) {
      val distance = sqrt(
        (incident.x - center.first).toDouble().pow(2) + (incident.y - center.second).toDouble()
          .pow(2)
      )
      distance <= radius
    } else false
  }

  override fun getPhoneNumber(): String {
    return kPhoneNumber
  }
}

class TriangleZone(private val points: List<Pair<Int, Int>>) : Zone() {
  private val kPhoneNumber = "84352835724"
  override fun isIncidentInside(incident: Incident?): Boolean {
    return if (incident!=null) {
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
