package decemo.com.userauthorisation.model

data class JwtTokenBody(
    val id: Long = -1,
    val role: String? = null
)
