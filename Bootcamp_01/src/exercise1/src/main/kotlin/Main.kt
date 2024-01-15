fun main() {
  val zone = readZone()
  printZoneInfo(zone)
  val incident = readIncident()
  printIncidentInfo(incident)
  if (zone!= null && zone.isIncidentInside(incident)){
    println("\nAn incident in the zone")
  } else {
    println("\nAn incident is not in the zone")
    println("Switch the applicant to the common number: 88008473824")
  }
}

fun readZone(): Zone? {
  println("Enter zone parameters:")
  val input = readLine() ?: return null
  val parts = input.split(" ")

  return try {
    when (parts.size) {
      2 -> parts[0].split(";")
        .let { (x, y) -> CircleZone(Pair(x.toInt(), y.toInt()), parts[1].toInt()) }
      3 -> {
        //можно переделать через map, но я пока не понимаю
        val coordinate_1 = parts[0].split(";")
        val x1 = coordinate_1[0].toInt()
        val y1 = coordinate_1[1].toInt()
        val coordinate_2 = parts[1].split(";")
        val x2 = coordinate_2[0].toInt()
        val y2 = coordinate_2[1].toInt()
        val coordinate_3 = parts[2].split(";")
        val x3 = coordinate_3[0].toInt()
        val y3 = coordinate_3[1].toInt()
        TriangleZone(listOf(Pair(x1, y1), Pair(x2, y2), Pair(x3, y3)))
      }
      4 -> {
        val coordinate_1 = parts[0].split(";")
        val x1 = coordinate_1[0].toInt()
        val y1 = coordinate_1[1].toInt()
        val coordinate_2 = parts[1].split(";")
        val x2 = coordinate_2[0].toInt()
        val y2 = coordinate_2[1].toInt()
        val coordinate_3 = parts[2].split(";")
        val x3 = coordinate_3[0].toInt()
        val y3 = coordinate_3[1].toInt()
        val coordinate_4 = parts[2].split(";")
        val x4 = coordinate_4[0].toInt()
        val y4 = coordinate_4[1].toInt()
        RectangleZone(listOf(Pair(x1, y1), Pair(x2, y2), Pair(x3, y3), Pair(x4, y4)))
      }
      else -> null
    }
  } catch (e: Exception) {
    println("Invalid input. Please enter valid zone parameters.")
    null
  }
}




fun printZoneInfo(zone: Zone?) {
  if (zone!=null) {
    println("\nThe zone info:")
    when (zone) {
      is CircleZone -> println("  The shape of zone: circle")
      is TriangleZone -> println("  The shape of zone: triangle")
      is RectangleZone -> println("  The shape of zone: rectangle")
    }
    println("  Phone number: ${zone.getPhoneNumber()}")
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
  fun getIncidentType(description:String):IncidentType?{
    return when{
      "CAT" in description.uppercase() &&  "TREE" in description.uppercase() -> IncidentType.CAT_ON_TREE
      else -> null
    }
  }
  println("\nEnter an incident coordinates:")
  val input = readLine() ?: return null
  val coordinates = input.split(";")
  val description = descriptions.random()
  val phone = phones.random()
  val type = getIncidentType(description)
  return Incident(coordinates[0].toInt(), coordinates[1].toInt(),description ,phone ,type )
}

fun printIncidentInfo(incident: Incident?){
  if (incident!=null){
    println("\nThe incident info:")
    println("  Description: ${incident.description}")
    println("  Phone number: ${incident.phoneNumber ?: "-"}")
    println("  Type:  ${incident.type?.type ?: "-"}")
  }
}