package com.github.yingzhuo.spring.cloud.examples.provider

import java.util.Optional

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.{EnableJpaAuditing, EnableJpaRepositories}
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan(Array(EntityPackage))
@Configuration
class ApplicationCnfJpa {

  @Bean
  def transactionManager(factory: EntityManagerFactory, dataSource: DataSource): PlatformTransactionManager = {
    val manager = new JpaTransactionManager(factory)
    manager.setDataSource(dataSource)
    manager
  }

  @Bean
  def auditorAware(): AuditorAware[String] = ApplicationCnfJpa.DefaultAuditorAware

}

object ApplicationCnfJpa {

  object DefaultAuditorAware extends AuditorAware[String] {
    override def getCurrentAuditor: Optional[String] = Optional.of("system")
  }

}
