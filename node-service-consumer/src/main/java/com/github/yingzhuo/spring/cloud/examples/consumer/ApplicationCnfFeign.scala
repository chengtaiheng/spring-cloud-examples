package com.github.yingzhuo.spring.cloud.examples.consumer

import java.util.Date

import org.apache.commons.lang3.time.DateFormatUtils
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry

@Configuration
class ApplicationCnfFeign extends FeignFormatterRegistrar {

  override def registerFormatters(registry: FormatterRegistry): Unit = {
    registry.addConverter(Date2Converter)
  }

  object Date2Converter extends Converter[Date, String] {
    override def convert(source: Date): String = DateFormatUtils.format(source, "yyyy-MM-dd'T'HH:mm:ss.SSS")
  }

}
