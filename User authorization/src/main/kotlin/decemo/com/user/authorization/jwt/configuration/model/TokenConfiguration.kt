package decemo.com.user.authorization.jwt.configuration.model

import java.time.Duration

data class TokenConfiguration(
    val expired: Duration,
    val secret: String
)