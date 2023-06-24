package decemo.com.bardatastore.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(value = ["decemo.com.bardatastore.repository"])
@EntityScan(value = ["decemo.com.bardatastore.entity"])
@ComponentScan(value = ["decemo.com.bardatastore.service"])
class DataStoreAutoConfiguration {

}