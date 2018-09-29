package com.github.yingzhuo.spring.cloud.examples.provider

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.jpa.repository.config.{EnableJpaAuditing, EnableJpaRepositories}
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan(Array("com.github.yingzhuo.spring.cloud.examples"))
@Configuration
class ApplicationCnfJpa {

  @Bean
  def transactionManager(factory: EntityManagerFactory, dataSource: DataSource): PlatformTransactionManager = {
    val manager = new JpaTransactionManager(factory)
    manager.setDataSource(dataSource)
    manager
  }

}
