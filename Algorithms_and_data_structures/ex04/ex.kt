fun main() {
  val n = readLine()!!.toInt()
  val books = readLine()!!.split(" ").map(String::toInt)
  val shortenedList = shortenBookList(books,n)
  println(shortenedList.joinToString(" "))
}

fun shortenBookList(numbers: List<Int>, n:Int): List<String> {
  val sortedNumbers = numbers.sorted()
  val result = mutableListOf<String>()

  for (i in 0 until n) {
    if(i > 0 && i < n-1 &&sortedNumbers[i] == sortedNumbers[i+1]-1 && sortedNumbers[i] == sortedNumbers[i - 1] + 1){
      result.add("...")
    } else {
      result.add("${sortedNumbers[i]}")
    }
  }
  return result
}


