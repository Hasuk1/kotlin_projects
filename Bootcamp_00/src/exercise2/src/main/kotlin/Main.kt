fun main() {
  try {
    val order = readOrder()
    val inputNumber = readInputNumber()
    val numberSet = createNumberSet(inputNumber, order)
    checkForPrimes(numberSet)
  } catch (e: Exception) {
    println("Error: ${e.message}")
  }
}

fun readOrder(): Boolean {
  try {
    print("The grouping order is (lower or higher): ")
    val orderNumber = readLine() ?: throw NumberFormatException()
    return when (orderNumber) {
      "higher" -> true
      "lower" -> false
      else -> throw IllegalArgumentException("Incorrect selection of grouping order.")
    }
  } catch (e: NumberFormatException) {
    throw Exception("Incorrect selection of grouping order.")
  } catch (e: IllegalArgumentException) {
    throw Exception(e.message)
  }
}


fun readInputNumber(): Int {
  try {
    println("Enter a number: ")
    val input = readLine() ?: throw NumberFormatException("An incorrect number has been entered.")
    val modifiedInput = if (input.isNotEmpty() && input.first() == '-') input.substring(1) else input
    return modifiedInput.toInt()
  } catch (e: NumberFormatException) {
    throw Exception("An incorrect number has been entered.")
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

fun checkForPrimes(numberSet: List<Int>) {
  println("Result:")
  for (num in numberSet) {
    if (isPrime(num)) {
      println("$num - prime")
    } else {
      println(num)
    }
  }
}

fun isPrime(num: Int): Boolean {
  if (num < 2) return false
  for (i in 2 until num) {
    if (num % i == 0) return false
  }
  return true
}
