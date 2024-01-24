package exercise2.data

data class Resume(
  val candidate_info: CandidateInfo,
  val education : List<Education>,
  val job_experience:List<Job>,
  val free_form: String
)
