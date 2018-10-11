package com.github.yingzhuo.spring.cloud.examples.consumer.action.advice

import com.netflix.hystrix.exception.HystrixBadRequestException
import com.typesafe.scalalogging.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

@RestControllerAdvice
class ExceptionHandlers {

  val log = Logger(getClass)

  @ExceptionHandler
  @ResponseStatus(HttpStatus.OK)
  def handleException(ex: HystrixBadRequestException): Map[String, AnyRef] = {
    val innerEx = ex.getCause
    Map("error" -> innerEx.getMessage)
  }

}
