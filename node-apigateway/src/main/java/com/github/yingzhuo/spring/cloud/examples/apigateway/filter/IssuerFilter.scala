package com.github.yingzhuo.spring.cloud.examples.apigateway.filter

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import org.springframework.stereotype.Component

object IssuerFilter {

  private val Null: AnyRef = new Object

}

@Component
class IssuerFilter extends ZuulFilter {

  override def filterType(): String = "pre"

  override def filterOrder(): Int = 0

  override def shouldFilter(): Boolean = true

  override def run(): AnyRef = {
    RequestContext.getCurrentContext.addZuulRequestHeader("Issuer", "应卓")
    IssuerFilter.Null
  }

}
