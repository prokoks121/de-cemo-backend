package decemo.com.userauthorisation.configuration

import decemo.com.userauthorisation.client.JwtClient
import decemo.com.userauthorisation.interceptor.JwtInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration(val jwtClient: JwtClient, val configuration: JwtClientConfiguration) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(JwtInterceptor(jwtClient))
            .excludePathPatterns(configuration.exclude)
    }
}