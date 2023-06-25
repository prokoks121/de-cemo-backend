package decemo.com.bardatastore.configuration

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(value = ["decemo.com.bardatastore.repository"])
@EntityScan(value = ["decemo.com.bardatastore.entity"])
//@EnableConfigurationProperties(DataSourceProperties::class)
//@ConditionalOnClass(DataSource::class)
class DataStoreConfiguration {

//    @Bean
//    @Primary
//    @ConfigurationProperties("decemo.db")
//    fun databaseDataSourceProperties(): DataSourceProperties {
//        return DataSourceProperties()
//    }
//
//    @Bean
//    @Primary
//    @ConditionalOnBean(name = ["databaseDataSourceProperties"])
//    fun dataSource(): DataSource? {
//        return databaseDataSourceProperties()
//            .initializeDataSourceBuilder()
//            .build()
//    }
//
//    @Bean
//    @Primary
//    @ConditionalOnBean(name = ["dataSource"])
//    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean? {
//        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
//        entityManagerFactoryBean.dataSource = dataSource()!!
//        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
//        entityManagerFactoryBean.setPackagesToScan("decemo.com.bardatastore.entity")
//        return entityManagerFactoryBean
//    }
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(type = ["JpaTransactionManager"])
//    fun jpaTransactionManager(entityManagerFactory: EntityManagerFactory?): JpaTransactionManager? {
//        val transactionManager = JpaTransactionManager()
//        transactionManager.entityManagerFactory = entityManagerFactory
//        return transactionManager
//    }
}