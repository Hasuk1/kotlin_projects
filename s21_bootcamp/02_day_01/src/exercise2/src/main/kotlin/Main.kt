fun main() {
  try {
    val zone = readZone() ?: throw IllegalArgumentException("Invalid zone input.")
    printZoneInfo(zone)
    printIncidentInfo(
      readIncident() ?: throw IllegalArgumentException("Invalid incident input."), zone
    )
  } catch (e: Exception) {
    println("Error: ${e.message}")
  }
}

fun String.phoneMask(): String {
  val digitsOnly = this.filter { it.isDigit() }
  return when {
    (digitsOnly.startsWith("7800") || digitsOnly.startsWith("8800")) -> {
      "8 (800) ${digitsOnly.substring(4, 7)} ${
        digitsOnly.substring(7, 9)
      } ${digitsOnly.substring(9)}"
    }

    (digitsOnly.startsWith("7") || digitsOnly.startsWith("8")) -> {
      "+7 ${digitsOnly.substring(1, 4)} ${digitsOnly.substring(4, 7)}-${
        digitsOnly.substring(7, 9)
      }-${digitsOnly.substring(9)}"
    }

    else -> this
  }
}

fun printZoneInfo(zone: Zone?) {
  if (zone != null) {
    println("\nThe zone info:")
    when (zone) {
      is CircleZone -> println("  The shape of zone: circle")
      is TriangleZone -> println("  The shape of zone: triangle")
      is RectangleZone -> println("  The shape of zone: rectangle")
    }
    println("  Phone number: ${zone.getPhoneNumber().phoneMask()}")
  }
}

fun printIncidentInfo(incident: Incident?, zone: Zone?) {
  if (incident != null) {
    println("\nThe incident info:")
    println("  Description: ${incident.description}")
    println("  Phone number: ${incident.phoneNumber?.phoneMask() ?: "-"}")
    println("  Type:  ${incident.type?.type ?: "-"}")
  }
  if (zone != null && zone.isIncidentInside(incident)) {
    println("\nAn incident in the zone")
  } else {
    println("\nAn incident is not in the zone")
    println("Switch the applicant to the common number: ${"88008473824".phoneMask()}")
  }
}
