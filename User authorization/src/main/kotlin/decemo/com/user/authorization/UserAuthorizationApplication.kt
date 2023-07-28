package decemo.com.user.authorization

import decemo.com.user.authorization.jwt.configuration.JwtConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(JwtConfiguration::class)
class UserAuthorizationApplication

fun main(args: Array<String>) {
    runApplication<UserAuthorizationApplication>(*args)
}
