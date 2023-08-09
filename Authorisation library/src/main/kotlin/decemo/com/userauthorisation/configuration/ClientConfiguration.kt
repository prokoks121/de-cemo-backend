package decemo.com.userauthorisation.configuration

import decemo.com.userauthorisation.client.JwtClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfiguration {
    @Bean
    @ConditionalOnMissingBean(RestTemplate::class)
    fun getRestTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }

    @Bean
    @ConditionalOnMissingBean(JwtClient::class)
    fun getJwtClient(restTemplate: RestTemplate, configuration: JwtClientConfiguration): JwtClient {
        return JwtClient(restTemplate, configuration)
    }
}