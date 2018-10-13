package com.github.yingzhuo.spring.cloud.examples.consumer

import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry

@Configuration
class ApplicationCnfFeign extends FeignFormatterRegistrar {

  override def registerFormatters(registry: FormatterRegistry): Unit = {
    registry.addConverter(FeignDate2StringConverter)
  }

}
