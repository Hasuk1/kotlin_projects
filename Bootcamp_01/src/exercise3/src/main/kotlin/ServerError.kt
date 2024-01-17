sealed class ServerError(val code: Int, val title: String, val description: String) {
  data class UnknownError(val unknownCode: Int) :
    ServerError(unknownCode, "Unknown Error", "Unknown error. Try again later.")

  data class InternalError(
    val internalCode: Int,
    val internalTitle: String,
    val internalDescription: String
  ) : ServerError(internalCode, internalTitle, internalDescription)

  companion object {
    fun createError(code: Int): ServerError {
      return when (code) {
        1000 -> InternalError(1000, "The user is not identified", "User is not identified.")
        1001 -> InternalError(1001, "The session is expired", "Session has expired.")
        1002 -> InternalError(1002, "No connection", "There is no internet connection. Try later.")
        1003 -> InternalError(
          1003,
          "The device has failed the verification",
          "The device did not pass the verification."
        )

        else -> UnknownError(code)
      }
    }
  }
}