sealed class ServerError(val code: Int, val title: String, val description: String) {
  data class UnknownError(val unknownCode: Int) :
    ServerError(unknownCode, "Unknown Error", "Unknown error. Try again later.")

  data class InternalError(
    val iCode: Int, val iTitle: String, val iDescription: String
  ) : ServerError(iCode, iTitle, iDescription)

  companion object {
    fun createError(code: Int): ServerError {
      return when (code) {
        1000 -> InternalError(1000, "The user is not identified", "User is not identified.")
        1001 -> InternalError(1001, "The session is expired", "Session has expired.")
        1002 -> InternalError(1002, "No connection", "There is no internet connection. Try later.")
        1003 -> InternalError(1003, "Device not verified", "The device failed the verification.")
        else -> UnknownError(code)
      }
    }
  }
}