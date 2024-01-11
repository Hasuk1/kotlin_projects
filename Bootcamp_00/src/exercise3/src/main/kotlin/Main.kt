fun main() {
  val outputMode = readOutputMode()
  val temperatureInput = readTemperature()
  val seasonInput = readSeason()

  val comfortableTemperatureRange = when (seasonInput) {
    Season.SUMMER -> 22f..25f
    Season.WINTER -> 20f..22f
  }

  val comfortable = comfortableTemperatureRange.contains(temperatureInput)

  println("The temperature is $temperatureInput ˚C")
  println("The comfortable temperature is from ${comfortableTemperatureRange.start} to ${comfortableTemperatureRange.endInclusive} ˚C.")

  if (!comfortable) {
    val adjustment = if (temperatureInput < comfortableTemperatureRange.start)
      "warmer by ${comfortableTemperatureRange.start - temperatureInput} degrees"
    else
      "cooler by ${temperatureInput - comfortableTemperatureRange.endInclusive} degrees"

    println("Please, make it $adjustment.")
  }
}

enum class Mode {
  CELSIUS, KELVIN, FAHRENHEIT
}

fun readOutputMode(): Mode {
  try {
    print("Output mode: ")
    val input = readLine() ?: throw NumberFormatException()
    return when (input) {
      "Celsius" -> Mode.CELSIUS
      "Kelvin" -> Mode.KELVIN
      "Fahrenheit" -> Mode.FAHRENHEIT
      else -> Mode.CELSIUS
    }
  } catch ()
}

fun readTemperature(): Float {
  while (true) {
    try {
      print("Enter a temperature: ")
      val input = readLine()?.toFloatOrNull()
      if (input != null) {
        return input
      } else {
        println("Incorrect input. Please enter a valid temperature.")
      }
    } catch (e: NumberFormatException) {
      println("Failed to parse the temperature. Please try again.")
    }
  }
}

enum class Season {
  SUMMER, WINTER
}

fun readSeason(): Season {
  while (true) {
    println("Enter a season - (W)inter or (S)ummer: ")
    val input = readLine()?.uppercase()
    if (input == "W" || input == "WINTER") {
      return Season.WINTER
    } else if (input == "S" || input == "SUMMER") {
      return Season.SUMMER
    } else {
      println("Incorrect input. Please enter either 'W' for Winter or 'S' for Summer.")
    }
  }
}
