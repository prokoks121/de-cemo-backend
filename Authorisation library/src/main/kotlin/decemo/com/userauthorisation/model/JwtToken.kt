package decemo.com.userauthorisation.model

import java.util.*

data class JwtToken(
    val token: String,
    val refreshToken: String,
    val expiredAt: Date
)
