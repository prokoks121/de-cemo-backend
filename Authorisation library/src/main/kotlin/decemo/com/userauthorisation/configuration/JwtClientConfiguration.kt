package decemo.com.userauthorisation.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "decemo.jwt")
@Configuration
class JwtClientConfiguration{
    lateinit var baseUrl:String
    var exclude: List<String> = listOf()
}
