package decemo.com.user.authorization.jwt.model

import java.util.*

data class JwtToken(
    val userId: Long,
    val expiredTime: Date
)