fun main() {
  try {
    val zone = readZone() ?: throw IllegalArgumentException("Invalid incident input.")
    printZoneInfo(zone)
    val incident = readIncident() ?: throw IllegalArgumentException("Invalid incident input.")
    printIncidentInfo(incident, zone)
  } catch (e: Exception) {
    println("Error: ${e.message}")
  }
}

fun readZone(): Zone? {
  println("Enter zone parameters:")
  return try {
    val input = readLine() ?: throw Exception()
    val parts = input.split(" ")
    when (parts.size) {
      2 -> parts[0].split(";").let { (x, y) ->
        CircleZone(
          Pair(
            x.toIntOrNull() ?: throw Exception(),
            y.toIntOrNull() ?: throw Exception()
          ), parts[1].toIntOrNull() ?: throw Exception()
        )
      }

      3 -> {
        TriangleZone(
          listOf(parts[0].split(";").let { (x, y) ->
            Pair(
              x.toIntOrNull() ?: throw Exception(),
              y.toIntOrNull() ?: throw Exception()
            )
          },
            parts[1].split(";").let { (x, y) ->
              Pair(
                x.toIntOrNull() ?: throw Exception(),
                y.toIntOrNull() ?: throw Exception()
              )
            },
            parts[2].split(";").let { (x, y) ->
              Pair(
                x.toIntOrNull() ?: throw Exception(),
                y.toIntOrNull() ?: throw Exception()
              )
            })
        )
      }

      4 -> {
        RectangleZone(
          listOf(parts[0].split(";").let { (x, y) ->
            Pair(
              x.toIntOrNull() ?: throw Exception(),
              y.toIntOrNull() ?: throw Exception()
            )
          },
            parts[1].split(";").let { (x, y) ->
              Pair(
                x.toIntOrNull() ?: throw Exception(),
                y.toIntOrNull() ?: throw Exception()
              )
            },
            parts[2].split(";").let { (x, y) ->
              Pair(
                x.toIntOrNull() ?: throw Exception(),
                y.toIntOrNull() ?: throw Exception()
              )
            },
            parts[3].split(";").let { (x, y) ->
              Pair(
                x.toIntOrNull() ?: throw Exception(),
                y.toIntOrNull() ?: throw Exception()
              )
            })
        )
      }

      else -> throw Exception()
    }
  } catch (e: Exception) {
    null
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
    println("  Phone number: ${zone.getPhoneNumber()}\n")
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
  println("Enter an incident coordinates:")
  return try {
    val input = readLine() ?: throw Exception()
    if (input.split(" ").size != 1) throw Exception()
    val coordinates = input.split(";").map {
      it.toIntOrNull() ?: throw Exception()
    }
    if (coordinates.size != 2) throw Exception()
    val description = descriptions.random()
    val phone = phones.random()
    val type = getIncidentType(description)
    Incident(coordinates[0], coordinates[1], description, phone, type)
  } catch (e: Exception) {
    null
  }
}

fun printIncidentInfo(incident: Incident?, zone: Zone?) {
  if (incident != null) {
    println("\nThe incident info:")
    println("  Description: ${incident.description}")
    println("  Phone number: ${incident.phoneNumber ?: "-"}")
    println("  Type:  ${incident.type?.type ?: "-"}")
  }
  if (zone != null && zone.isIncidentInside(incident)) {
    println("\nAn incident in the zone")
  } else {
    println("\nAn incident is not in the zone")
    println("Switch the applicant to the common number: 88008473824")
  }
}