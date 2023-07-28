package decemo.com.user.authorization.controller.model

import java.util.*

data class EncodedToken(
    var token: String,
    var refreshToken: String,
    var expiredAt: Date
)
