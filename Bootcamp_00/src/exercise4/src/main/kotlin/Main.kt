fun main() {
  val outputMode = readOutputMode()
  val seasonInput = readSeason()
  val temperatureInput = setTemperatureAsMod(outputMode, readTemperature())
  val humidityInput = readHumidity()
  val modeSymbol = getModeSymbol(outputMode)
  printTemperatureInfo(temperatureInput, getComfortableTemperatureRange(seasonInput, outputMode), modeSymbol)
  printHumidityInfo(humidityInput, getComfortableHumidityRange(seasonInput))
}

fun readHumidity(): Int {
  while (true) {
    try {
      println("Enter humidity in percent: ")
      val input = readLine()?.toIntOrNull()
      if (input != null && input >= 0 && input <= 100) {
        return input
      } else {
        println("Incorrect input. Please enter a valid humidity.")
      }
    } catch (e: NumberFormatException) {
      println("Failed to parse the humidity. Please try again.")
    }
  }
}

fun getComfortableHumidityRange(seasonInput: Season): ClosedRange<Int> {
  return when (seasonInput) {
    Season.SUMMER -> 30..60
    Season.WINTER -> 30..45
  }
}

fun printTemperatureInfo(temperatureInput: Float, comfortableTemperatureRange: ClosedRange<Float>, modeSymbol: String) {
  val comfortableTemperature = temperatureInput in comfortableTemperatureRange
  println("\nThe temperature is %.2f $modeSymbol".format(temperatureInput))
  println("The comfortable temperature is from ${comfortableTemperatureRange.start} $modeSymbol to ${comfortableTemperatureRange.endInclusive} $modeSymbol")
  if (!comfortableTemperature) {
    val adjustment = if (temperatureInput < comfortableTemperatureRange.start) "warmer by %.2f $modeSymbol".format(
      comfortableTemperatureRange.start - temperatureInput
    )
    else "cooler by %.2f $modeSymbol".format(temperatureInput - comfortableTemperatureRange.endInclusive)
    println("Please, make it $adjustment.")
  } else {
    println("The temperature is comfortable")
  }
}

fun printHumidityInfo(humidityInput: Int, comfortableHumidityRange: ClosedRange<Int>) {
  val comfortableHumidity = humidityInput in comfortableHumidityRange
  println("\nThe comfortable humidity is from ${comfortableHumidityRange.start}% to ${comfortableHumidityRange.endInclusive}%")
  if (!comfortableHumidity) {
    val adjustment =
      if (humidityInput < comfortableHumidityRange.start) "higher by ${comfortableHumidityRange.start - humidityInput}%"
      else "lower by ${humidityInput - comfortableHumidityRange.endInclusive}%"
    println("Please make the humidity $adjustment.")
  } else {
    println("The humidity is comfortable")
  }
}