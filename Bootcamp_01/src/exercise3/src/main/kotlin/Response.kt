sealed class Response() {
  data class Success(val code: Int, val message: String = "Request successfully processed") :
    Response()

  data class Error(val error: ServerError) : Response()
}