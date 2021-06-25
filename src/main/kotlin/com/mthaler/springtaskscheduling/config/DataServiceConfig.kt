package com.mthaler.springtaskscheduling.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(basePackages = ["com.mthaler.springtaskscheduling.repos"])
@ComponentScan(basePackages  = ["com.mthaler.springtaskscheduling"] )
class DataServiceConfig {

    @Bean
    fun dataSource(): DataSource {
        return try {
            val dbBuilder = EmbeddedDatabaseBuilder()
            dbBuilder.setType(EmbeddedDatabaseType.H2).build()
        } catch (e: Exception) {
            logger.error("Embedded DataSource bean cannot be created!", e)
            throw e
        }
    }

    @Bean
    fun hibernateProperties(): Properties? {
        val hibernateProp = Properties()
        hibernateProp["hibernate.dialect"] = "org.hibernate.dialect.H2Dialect"
        hibernateProp["hibernate.hbm2ddl.auto"] = "create-drop"
        //hibernateProp.put("hibernate.format_sql", true);
        hibernateProp["hibernate.show_sql"] = true
        hibernateProp["hibernate.max_fetch_depth"] = 3
        hibernateProp["hibernate.jdbc.batch_size"] = 10
        hibernateProp["hibernate.jdbc.fetch_size"] = 50
        return hibernateProp
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory())
    }

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        return HibernateJpaVendorAdapter()
    }

    @Bean
    fun entityManagerFactory(): EntityManagerFactory {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.setPackagesToScan("com.apress.prospring5.ch11.entities")
        factoryBean.dataSource = dataSource()
        factoryBean.setJpaProperties(hibernateProperties()!!)
        factoryBean.jpaVendorAdapter = jpaVendorAdapter()
        factoryBean.afterPropertiesSet()
        return factoryBean.nativeEntityManagerFactory
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(DataServiceConfig::class.java)
    }
}