data class Incident(
    val x: Int,
    val y: Int,
    val type: IncidentType,
    val phoneNumber: String?
)

enum class IncidentType(val name: String) {
    FIRE("Fire"),
    LEAK("Gas Leak"),
    CAT("Cat on the tree")
}

open class Zone(
    val phoneNumber: String = "800"
) {
    open fun isIncidentInside(incident: Incident): Boolean {
        return false
    }
}

class CircleZone(
    phoneNumber: String,
    val centerX: Int,
    val centerY: Int,
    val radius: Int
) : Zone(phoneNumber) {
    override fun isIncidentInside(incident: Incident): Boolean {
        val distance = Math.sqrt((incident.x - centerX).toDouble().pow(2) + (incident.y - centerY).toDouble().pow(2))
        return distance <= radius
    }
}

class TriangleZone(
    phoneNumber: String,
    val point1: Pair<Int, Int>,
    val point2: Pair<Int, Int>,
    val point3: Pair<Int, Int>
) : Zone(phoneNumber) {
    override fun isIncidentInside(incident: Incident): Boolean {
        // Реализуйте логику для проверки, находится ли инцидент внутри треугольника
        return false
    }
}

class RectangleZone(
    phoneNumber: String,
    val point1: Pair<Int, Int>,
    val point2: Pair<Int, Int>,
    val point3: Pair<Int, Int>,
    val point4: Pair<Int, Int>
) : Zone(phoneNumber) {
    override fun isIncidentInside(incident: Incident): Boolean {
        // Реализуйте логику для проверки, находится ли инцидент внутри прямоугольника
        return false
    }
}

fun inputZone(): Zone? {
    println("Enter zone parameters:")
    val input = readLine() ?: return null

    val parts = input.split(" ")
    val phoneNumber = parts[0]
    val coordinates = parts.subList(1, parts.size).map { it.split(";").let { it[0].toInt() to it[1].toInt() } }

    return when (coordinates.size) {
        1 -> {
            val (centerX, centerY, radius) = coordinates[0]
            CircleZone(phoneNumber, centerX, centerY, radius)
        }
        3 -> {
            val (point1, point2, point3) = coordinates
            TriangleZone(phoneNumber, point1, point2, point3)
        }
        4 -> {
            val (point1, point2, point3, point4) = coordinates
            RectangleZone(phoneNumber, point1, point2, point3, point4)
        }
        else -> null
    }
}

fun main() {
    val zone = inputZone()
    if (zone != null) {
        println("The zone info:")
        when (zone) {
            is CircleZone -> println("  The shape of zone: circle")
            is TriangleZone -> println("  The shape of zone: triangle")
            is RectangleZone -> println("  The shape of zone: rectangle")
        }
        println("  Phone number: ${zone.phoneNumber}")

        println("Enter an incident coordinates:")
        val incidentX = readLine()?.toInt() ?: return
        val incidentY = readLine()?.toInt() ?: return

        val incident = Incident(incidentX, incidentY, IncidentType.FIRE, null)

        println("The incident info:")
        println("  Description: ${incident.type.name}")
        incident.phoneNumber?.let { println("  Phone number: $it") }

        if (zone.isIncidentInside(incident)) {
            println("An incident is in the zone")
        } else {
            println("An incident is not in the zone")
            println("Switch the applicant to the common number: 88008473824")
        }
    } else {
        println("Invalid input format.")
    }
}
