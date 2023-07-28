package decemo.com.user.authorization.jwt.model

import java.util.*

data class JwtToken(
    val id: Long,
    val expiredTime: Date
)