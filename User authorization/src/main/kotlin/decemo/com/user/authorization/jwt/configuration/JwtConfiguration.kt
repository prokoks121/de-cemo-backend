package decemo.com.user.authorization.jwt.configuration

import decemo.com.user.authorization.jwt.configuration.model.TokenConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties("decemo.jwt")
@Configuration
class JwtConfiguration {
    lateinit var token: TokenConfiguration
    lateinit var refreshToken: TokenConfiguration
}