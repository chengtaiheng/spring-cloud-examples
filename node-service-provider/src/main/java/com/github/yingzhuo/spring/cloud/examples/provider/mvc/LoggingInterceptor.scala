package com.github.yingzhuo.spring.cloud.examples.provider.mvc

import java.lang.reflect.Method
import java.util

import com.typesafe.scalalogging.Logger
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.apache.commons.lang3.StringUtils
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

import scala.util.Try

class LoggingInterceptor extends HandlerInterceptor {

  val log = Logger(getClass)

  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean = {

    handler match {
      case hm: HandlerMethod =>
        Try(doLog(request, hm))
        true
      case _ => true
    }
  }

  private def doLog(request: HttpServletRequest, handlerMethod: HandlerMethod): Unit = {
    log.debug("-" * 120)
    log.debug("[Path]: ")
    log.debug("\t\t\t{}", request.getRequestURI)
    log.debug("[Method]: ")
    log.debug("\t\t\t{}", request.getMethod)
    log.debug("[Headers]: ")
    val headerNames: util.Enumeration[String] = request.getHeaderNames
    while ( {
      headerNames.hasMoreElements
    }) {
      val name: String = headerNames.nextElement
      val value: String = request.getHeader(name)
      log.debug("\t\t\t{} = {}", name, if (name.equalsIgnoreCase("cookie")) StringUtils.abbreviate(value, 60)
      else value)
    }
    log.debug("[Params]: ")
    val paramNames: util.Enumeration[String] = request.getParameterNames
    while ( {
      paramNames.hasMoreElements
    }) {
      val name: String = paramNames.nextElement
      val value: String = request.getParameter(name)
      log.debug("\t\t\t{} = {}", name, value)
    }
    if (handlerMethod != null) {
      val method: Method = handlerMethod.getMethod
      val `type`: Class[_] = handlerMethod.getBeanType
      val methodDeprecated: Boolean = method.getAnnotation(classOf[Deprecated]) != null
      val typeDeprecated: Boolean = `type`.getAnnotation(classOf[Deprecated]) != null
      log.debug("[Controller]: ")
      log.debug("\t\t\ttype = {}{}", method.getName, if (typeDeprecated) "(Deprecated)" else "")
      log.debug("\t\t\tmethod-name = {}{}", `type`.getName, if (methodDeprecated) "(Deprecated)" else "")
    }
    log.debug("-" * 120)
  }

}
