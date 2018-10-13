package com.github.yingzhuo.spring.cloud.examples.provider.controller.advice

import com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException
import com.typesafe.scalalogging.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

@RestControllerAdvice
class ExceptionHandlers {

  val log = Logger(getClass)

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  def handleException(ex: BusinessException): Map[String, AnyRef] = {

    log.debug("业务异常: {}", ex.getMessage)

    Map(
      "type" -> classOf[BusinessException].getName,
      "message" -> ex.getMessage
    )

  }

}
