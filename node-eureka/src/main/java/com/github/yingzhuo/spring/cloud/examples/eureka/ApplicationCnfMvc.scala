package com.github.yingzhuo.spring.cloud.examples.eureka

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{ViewControllerRegistry, WebMvcConfigurer}

@Configuration
@ServletComponentScan
class ApplicationCnfMvc extends WebMvcConfigurer {

  @Value("${eureka.dashboard.path}")
  var eurekaDashboardPath: String = _

  override def addViewControllers(registry: ViewControllerRegistry): Unit = {
    registry.addRedirectViewController("", s"$eurekaDashboardPath")
    registry.addRedirectViewController("/", s"$eurekaDashboardPath")
  }

}
