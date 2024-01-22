fun main() {
  println("Type a response code:")
  try {
    val code = readLine()?.toInt() ?: throw NumberFormatException()
    val response = processResponseCode(code)
    printResponse(response)
  } catch (e: NumberFormatException) {
    println("Error: Input should be an integer.")
  }
}

fun processResponseCode(code: Int): Response {
  return if (code in 200..201) {
    Response.Success(code)
  } else {
    val error = when (code) {
      in 1000..1003 -> ServerError.createError(code)
      else -> ServerError.UnknownError(code)
    }
    Response.Error(error)
  }
}

fun printResponse(response: Response) {
  when (response) {
    is Response.Success -> println("\nSuccess:\n  Code: ${response.code}\n  Message: ${response.message}")
    is Response.Error -> println(
      "\nError:\n  Code: ${response.error.code}\n" + "  Title: ${response.error.title}\n" + "  Description: ${response.error.description}"
    )
  }
}
