fun convertNumberToWords(number: Int): String {
  val units = arrayOf(
    "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
  )
  val tens = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

  fun convertBelowHundred(n: Int): String {
    return when {
      n < 10 -> units[n]
      n < 20 -> units[n]
      else -> "${tens[n / 10]} ${units[n % 10]}"
    }
  }

  fun convertBelowThousand(n: Int): String {
    return when {
      n < 100 -> convertBelowHundred(n)
      else -> "${units[n / 100]} Hundred ${convertBelowHundred(n % 100)}"
    }
  }

  fun convertBelowMillion(n: Int): String {
    return when {
      n < 1000 -> convertBelowThousand(n)
      else -> "${convertBelowHundred(n / 1000)} Thousand ${convertBelowThousand(n % 1000)}"
    }
  }

  fun convertBelowBillion(n: Int): String {
    return when {
      n < 1000000 -> convertBelowMillion(n)
      n == 1000000000 -> "Billion"
      else -> "${convertBelowHundred(n / 1000000)} Million ${convertBelowMillion(n % 1000000)}"
    }
  }

  return when {
    number == 0 -> "Zero"
    number < 0 -> "Negative ${convertBelowBillion(-number)}"
    number <= 1000000000 -> convertBelowBillion(number)
    else -> "Number is too large"
  }
}

fun main() {
  println("Enter a number:")
  val input = readLine()?.toIntOrNull()

  if (input != null) {
    val result = convertNumberToWords(input)
    println(result)
  } else {
    println("Invalid input. Please enter a valid integer.")
  }
}
