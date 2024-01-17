fun main() {
  val n = readLine()!!.toInt()
  val books = readLine()!!.split(" ").map(String::toInt)
  val shortenedList = shortenBookList(books,n)
  println(shortenedList.joinToString(" "))
}

fun shortenBookList(numbers: List<Int>, n: Int): Set<String> {
  val sortedNumbers = numbers.sorted().toMutableList()
  println(sortedNumbers)
  val result = mutableSetOf<String>()
  var lastNumbers = Int.MAX_VALUE
  for (i in 0 until n) {
    if (sortedNumbers[i] == lastNumbers) {
      sortedNumbers.removeAt(i)
      continue
    }
    if (i > 0 && i < n - 1 && sortedNumbers[i] == sortedNumbers[i + 1] - 1 && sortedNumbers[i] == sortedNumbers[i - 1] + 1) {
      result.add("...")
    } else {
      result.add("${sortedNumbers[i]}")
      lastNumbers = sortedNumbers[i]
    }
  }
  return result
}


