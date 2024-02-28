package com.example.thermohydrometer.data

import com.example.core.utility.Mode
import com.example.core.utility.Season

fun getTemperatureInfo(mode: Mode, season: Season, temperatureInput: Float): String {
  val modeSymbol = getModeSymbol(mode)
  val comfortableTemperatureRange = getComfortableTemperatureRange(season, mode)
  val comfortable = temperatureInput in comfortableTemperatureRange
  val temperature = setTemperatureAsMod(mode, temperatureInput)
  var output = "Temperature is %.2f $modeSymbol".format(temperature)
  output += "\nComfortable from ${comfortableTemperatureRange.start} $modeSymbol to ${comfortableTemperatureRange.endInclusive} $modeSymbol"
  if (!comfortable) {
    val adjustment =
      if (temperature < comfortableTemperatureRange.start) "warmer by %.2f $modeSymbol".format(
        comfortableTemperatureRange.start - temperature
      )
      else "cooler by %.2f $modeSymbol".format(temperature - comfortableTemperatureRange.endInclusive)
    output += "\nPlease, make it $adjustment"
  }
  return output
}

fun getModeSymbol(outputMode: Mode): String {
  return when (outputMode) {
    Mode.CELSIUS -> "˚C"
    Mode.FAHRENHEIT -> "°F"
    Mode.KELVIN -> "K"
  }
}

fun getComfortableTemperatureRange(
  seasonInput: Season, outputMode: Mode
): ClosedFloatingPointRange<Float> {
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