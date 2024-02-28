package com.example.prime_numbers.data

import com.example.core.logger.Logger

fun getNumberPrimeInfo(isHigher: Boolean, inputNumber: Int): String {
  Logger.info("Entered number in prime module: $inputNumber | Module: Prime")
  return try {
    val numberSet =
      createNumberSet(if (inputNumber < 0) inputNumber * -1 else inputNumber, isHigher)
    checkForPrimes(numberSet)
  } catch (e: Exception) {
    Logger.error("Error: ${e.message} | Module: Prime")
    "Error: ${e.message}"
  }
}

fun createNumberSet(number: Int, order: Boolean): List<Int> {
  val numberSet = mutableListOf<Int>()
  val numberDigits = number.toString().toCharArray().map { it.digitToInt() }
  val indices = when (order) {
    true -> numberDigits.indices
    false -> numberDigits.indices.reversed()
  }
  var currentNumber = 0
  for (index in indices) {
    currentNumber = currentNumber * 10 + numberDigits[index]
    numberSet.add(currentNumber)
  }
  return numberSet
}

fun checkForPrimes(numberSet: List<Int>): String {
  var output = "Result:"
  numberSet.forEach { num ->
    output += if (isPrime(num)) {
      "\n$num - prime"
    } else {
      "\n$num"
    }
  }
  return output
}

fun isPrime(num: Int): Boolean {
  if (num < 2) return false
  for (i in 2 until num) if (num % i == 0) return false
  return true
}