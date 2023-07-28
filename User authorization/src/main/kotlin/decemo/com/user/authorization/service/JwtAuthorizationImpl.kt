package decemo.com.user.authorization.service

import decemo.com.user.authorization.jwt.JwtDecoder
import decemo.com.user.authorization.jwt.JwtEncoder
import decemo.com.user.authorization.jwt.model.JwtToken
import org.springframework.stereotype.Service

@Service
class JwtAuthorizationImpl(val encoder: JwtEncoder, val decoder: JwtDecoder) : JwtAuthorization {
    override fun encodeWithRefreshToken(userId: Long): Pair<String, String> {
        val token = encodeToken(userId)
        val refreshToken = encoder.refreshTokenEncode(userId)
        return Pair(token, refreshToken)
    }

    override fun encodeToken(userId: Long): String {
        return encoder.encode(userId)
    }

    override fun decodeToken(jwtToken: String): Result<JwtToken> {
        return decoder.decodeToken(jwtToken)
    }

    override fun encodeTokenFromRefreshToken(jwtToken: String): Result<Pair<String, String>> {
        val refreshToken = decoder.decodeRefreshToken(jwtToken)
        refreshToken.onSuccess {
            val newRefreshToken = encoder.refreshTokenEncode(it.id)
            val newToken = encoder.encode(it.id)
            return Result.success(Pair(newToken, newRefreshToken))
        }.onFailure {
            return Result.failure(it)
        }
        throw Exception()
    }
}