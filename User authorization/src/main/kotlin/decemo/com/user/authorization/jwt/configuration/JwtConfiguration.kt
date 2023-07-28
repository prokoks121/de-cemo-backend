package decemo.com.user.authorization.jwt.configuration

import decemo.com.user.authorization.jwt.configuration.model.TokenConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("decemo.jwt")
class JwtConfiguration(
    var token: TokenConfiguration,
    var refreshToken: TokenConfiguration
)