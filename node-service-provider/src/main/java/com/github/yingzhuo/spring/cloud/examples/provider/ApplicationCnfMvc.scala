package com.github.yingzhuo.spring.cloud.examples.provider

import com.github.yingzhuo.spring.cloud.examples.provider.mvc.LoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.core.{Ordered => SpringOrdered}
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurer}

@Configuration
class ApplicationCnfMvc extends WebMvcConfigurer {

  override def addInterceptors(registry: InterceptorRegistry): Unit = {
    registry.addInterceptor(new LoggingInterceptor).addPathPatterns("/", "/**").order(SpringOrdered.HIGHEST_PRECEDENCE)
  }

}
