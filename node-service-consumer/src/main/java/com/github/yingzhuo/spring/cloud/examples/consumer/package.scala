package com.github.yingzhuo.spring.cloud.examples

import java.util.Date

import org.apache.commons.lang3.time.DateFormatUtils
import org.springframework.core.convert.converter.Converter

package object consumer {

  private val FeignDatePattern: String = "yyyy-MM-dd'T'HH:mm:ss.SSS"

  object FeignDate2StringConverter extends Converter[Date, String] {
    override def convert(date: Date): String = DateFormatUtils.format(date, FeignDatePattern)
  }

}
