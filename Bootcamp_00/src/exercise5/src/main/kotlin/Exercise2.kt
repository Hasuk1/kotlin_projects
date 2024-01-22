fun readCoordinate(prompt: String): Double {
  while (true) {
    try {
      println(prompt)
      return readLine()?.toDouble() ?: throw NumberFormatException()
    } catch (e: NumberFormatException) {
      println("Failed to parse the number. Please try again.")
    }
  }
}

fun readRadius(prompt: String): Double {
  while (true) {
    try {
      println(prompt)
      val radius = readLine()?.toDouble() ?: throw NumberFormatException()
      if (radius < 0) throw IllegalArgumentException("Radius must be non-negative.")
      return radius
    } catch (e: NumberFormatException) {
      println("Failed to parse the number. Please try again.")
    } catch (e: IllegalArgumentException) {
      println(e.message)
    }
  }
}