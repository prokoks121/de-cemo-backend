package decemo.com.user.authorization.service

import decemo.com.user.authorization.jwt.model.JwtToken

interface JwtAuthorization {
    fun encodeToken(userId: Long): String

    fun decodeToken(jwtToken: String): Result<JwtToken>

    fun encodeTokenFromRefreshToken(jwtToken: String): Result<Pair<String, String>>
}