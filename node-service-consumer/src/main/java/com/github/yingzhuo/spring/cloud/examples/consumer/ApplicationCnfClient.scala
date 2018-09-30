package com.github.yingzhuo.spring.cloud.examples.consumer

import java.util.Date

import org.apache.commons.lang3.time.DateFormatUtils
import org.springframework.cloud.openfeign.FeignFormatterRegistrar
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry

@Configuration
class ApplicationCnfClient extends FeignFormatterRegistrar {

  override def registerFormatters(registry: FormatterRegistry): Unit = {
    registry.addConverter(Date2StringConverter)
  }

  private object Date2StringConverter extends Converter[Date, String] {
    override def convert(source: Date): String = {
      DateFormatUtils.format(source, "yyyy-MM-dd HH:mm:ss.SSS")
    }
  }

}
