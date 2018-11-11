package com.github.yingzhuo.spring.cloud.examples.eureka

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationCnfJackson {

  @Autowired
  def config(om: ObjectMapper): Unit = om.registerModule(DefaultScalaModule)

}
