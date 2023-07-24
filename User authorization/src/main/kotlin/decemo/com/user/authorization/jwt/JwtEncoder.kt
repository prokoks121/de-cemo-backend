package decemo.com.user.authorization.jwt

import decemo.com.user.authorization.jwt.configuration.JwtConfiguration
import decemo.com.user.authorization.jwt.configuration.model.TokenConfiguration
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtEncoder(val jwtConfiguration: JwtConfiguration) {

    fun encode(userId: Long): String {
        return buildToken(userId, jwtConfiguration.token)
    }

    fun refreshTokenEncode(userId: Long): String {
        return buildToken(userId, jwtConfiguration.refreshToken)
    }

    private fun buildToken(userId: Long, configuration: TokenConfiguration): String {
        return Jwts
            .builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + configuration.expired.toMillis()))
            .signWith(SignatureAlgorithm.HS512, configuration.secret)
            .compact()
    }
}