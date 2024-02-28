package com.example.feature.speech.data

import com.example.core.logger.Logger

fun convertNumberToText(number: Int): String {
  Logger.info("Entered number is $number | Module: Speech")
  val units = arrayOf(
    "",
    "One",
    "Two",
    "Three",
    "Four",
    "Five",
    "Six",
    "Seven",
    "Eight",
    "Nine",
    "Ten",
    "Eleven",
    "Twelve",
    "Thirteen",
    "Fourteen",
    "Fifteen",
    "Sixteen",
    "Seventeen",
    "Eighteen",
    "Nineteen"
  )
  val tens =
    arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

  fun convertTens(n: Int): String {
    return when {
      n < 20 -> units[n]
      else -> "${tens[n / 10]}${if (n % 10 != 0) "-" + units[n % 10] else ""}"
    }
  }

  fun convertHundred(n: Int): String {
    return when {
      n < 100 -> convertTens(n)
      else -> "${units[n / 100]}-Hundred${if (n % 100 != 0) "-" + convertTens(n % 100) else ""}"
    }
  }

  fun convertThousand(n: Int): String {
    return when {
      n < 1000 -> convertHundred(n)
      else -> {
        "${convertHundred(n / 1000)}-Thousand${if (n % 1000 != 0) "-" + convertHundred(n % 1000) else ""}"
      }
    }
  }

  fun convertMillion(n: Int): String {
    return when {
      n < 1000000 -> convertThousand(n)
      else -> {
        "${convertHundred(n / 1000000)}-Million${if (n % 1000 != 0) "-" + convertThousand(n % 1000000) else ""}"
      }
    }
  }

  fun convertBillion(n: Int): String {
    return when {
      n < 1000000000 -> convertMillion(n)
      else -> {
        "${convertHundred(n / 1000000000)}-Billion${if (n % 1000 != 0) "-" + convertMillion(n % 1000000000) else ""}"
      }
    }
  }

  return when {
    number < 0 -> "Negative ${convertBillion(-number)}"
    number == 0 -> "Zero"
    number <= Int.MAX_VALUE -> convertBillion(number)
    else -> {
      Logger.warning("Entered number is too large. | Module: Speech")
      "Number is too large"
    }
  }
}