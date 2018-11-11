package com.github.yingzhuo.spring.cloud.examples.consumer.cli.support

import java.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException
import com.netflix.hystrix.exception.HystrixBadRequestException
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.stereotype.Component

import scala.collection.JavaConverters._
import scala.util.{Success, Try}

@Component
class ThrowableDecoder extends ErrorDecoder {

  override def decode(methodKey: String, response: Response): Exception = {
    Try(
      createException(methodKey, response)
    ) match {
      case Success(e) => e
      case _ => new ErrorDecoder.Default().decode(methodKey, response)
    }
  }

  private def createException(methodKey: String, response: Response): Exception = {
    val body = response.body().asReader()
    val info = ThrowableDecoder.ObjectMapper.readValue(body, classOf[util.Map[String, AnyRef]]).asScala

    (info("type"), info("message")) match {
      case ("com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException", msg: String) => new HystrixBadRequestException(msg, BusinessException(msg))
      case _ => new ErrorDecoder.Default().decode(methodKey, response)
    }
  }

}

object ThrowableDecoder {

  val ObjectMapper: ObjectMapper = new ObjectMapper()

}
