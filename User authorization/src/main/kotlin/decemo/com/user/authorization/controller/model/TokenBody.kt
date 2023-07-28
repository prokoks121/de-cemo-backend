package decemo.com.user.authorization.controller.model

data class TokenBody(
    val id: Long,
    val role: String?
)