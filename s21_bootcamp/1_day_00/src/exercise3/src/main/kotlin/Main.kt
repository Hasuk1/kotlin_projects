fun main() {
  val outputMode = readOutputMode()
  val seasonInput = readSeason()
  var temperatureInput = readTemperature()
  val modeSymbol = getModeSymbol(outputMode)
  val comfortableTemperatureRange = getComfortableTemperatureRange(seasonInput, outputMode)
  val comfortable = temperatureInput in comfortableTemperatureRange
  temperatureInput = setTemperatureAsMod(outputMode, temperatureInput)
  println("\nThe temperature is %.2f $modeSymbol".format(temperatureInput))
  println("The comfortable temperature is from ${comfortableTemperatureRange.start} $modeSymbol to ${comfortableTemperatureRange.endInclusive} $modeSymbol")
  if (!comfortable) {
    val adjustment = if (temperatureInput < comfortableTemperatureRange.start) "warmer by %.2f $modeSymbol".format(
      comfortableTemperatureRange.start - temperatureInput
    )
    else "cooler by %.2f $modeSymbol".format(temperatureInput - comfortableTemperatureRange.endInclusive)
    println("Please, make it $adjustment.")
  }
}

enum class Mode {
  CELSIUS, KELVIN, FAHRENHEIT
}

fun readOutputMode(): Mode {
  while (true) {
    try {
      print("Output mode: ")
      val input = readLine()
        ?: throw IllegalArgumentException("The output format is not correct. Standard format - Celsius is set")
      return when (input.uppercase()) {
        "CELSIUS" -> Mode.CELSIUS
        "KELVIN" -> Mode.KELVIN
        "FAHRENHEIT" -> Mode.FAHRENHEIT
        else -> {
          println("The output format is not correct. Standard format - Celsius is set")
          return Mode.CELSIUS
        }
      }
    } catch (e: IllegalArgumentException) {
      println(e.message)
      return Mode.CELSIUS
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

fun readTemperature(): Float {
  while (true) {
    try {
      println("Enter a temperature in Celsius (˚C): ")
      val input = readLine()?.toFloatOrNull()
      if (input != null && input >= -50 && input <= 50) {
        return input
      } else {
        println("Incorrect input. Please enter a valid temperature.")
      }
    } catch (e: NumberFormatException) {
      println("Failed to parse the temperature. Please try again.")
    }
  }
}

fun getModeSymbol(outputMode: Mode): String {
  return when (outputMode) {
    Mode.CELSIUS -> "˚C"
    Mode.FAHRENHEIT -> "°F"
    Mode.KELVIN -> "K"
  }
}

fun getComfortableTemperatureRange(seasonInput: Season, outputMode: Mode): ClosedFloatingPointRange<Float> {
  return when (outputMode) {
    Mode.CELSIUS -> when (seasonInput) {
      Season.SUMMER -> 22f..25f
      Season.WINTER -> 20f..22f
    }

    Mode.KELVIN -> when (seasonInput) {
      Season.SUMMER -> 295.15f..298.15f
      Season.WINTER -> 293.15f..295.15f
    }

    Mode.FAHRENHEIT -> when (seasonInput) {
      Season.SUMMER -> 71.6f..77f
      Season.WINTER -> 68f..71.6f
    }
  }
}

fun setTemperatureAsMod(outputMode: Mode, temperatureInput: Float): Float {
  return when (outputMode) {
    Mode.CELSIUS -> temperatureInput
    Mode.FAHRENHEIT -> (temperatureInput * 9 / 5) + 32
    Mode.KELVIN -> (temperatureInput + 273.15f)
  }
}
