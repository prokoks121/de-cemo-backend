package decemo.com.user.toLibrary.configuration

import decemo.com.user.toLibrary.client.JwtClient
import decemo.com.user.toLibrary.interceptor.JwtInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration(val jwtClient: JwtClient, val configuration: JwtClientConfiguration) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        println("Radio")
        registry.addInterceptor(JwtInterceptor(jwtClient))
//            .addPathPatterns("/**")
            .excludePathPatterns(configuration.exclude)
    }
}