package decemo.com.user.authorization.jwt

import decemo.com.user.authorization.jwt.configuration.JwtConfiguration
import decemo.com.user.authorization.jwt.configuration.model.TokenConfiguration
import decemo.com.user.authorization.jwt.model.JwtToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class JwtDecoder(val configuration: JwtConfiguration) {

    fun decodeToken(jwtToken: String): Result<JwtToken> {
        return resolveToken(jwtToken, configuration.token)
    }

    fun decodeRefreshToken(jwtToken: String): Result<JwtToken> {
        return resolveToken(jwtToken, configuration.refreshToken)
    }

    fun resolveToken(jwtToken: String, configuration: TokenConfiguration): Result<JwtToken> {
        return runCatching {
            val jwtParsedToken = Jwts
                .parser()
                .setSigningKey(configuration.secret)
                .parseClaimsJws(jwtToken)
            jwtParsedToken.mapTo()
        }
    }

    private fun Jws<Claims>.mapTo(): JwtToken {
        return JwtToken(
            body.subject.toLong(),
            body.expiration
        )
    }
}