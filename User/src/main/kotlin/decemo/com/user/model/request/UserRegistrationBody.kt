package decemo.com.user.model.request

data class UserRegistrationBody(
    val email: String,
    val password: String,
    val fullName: String
)