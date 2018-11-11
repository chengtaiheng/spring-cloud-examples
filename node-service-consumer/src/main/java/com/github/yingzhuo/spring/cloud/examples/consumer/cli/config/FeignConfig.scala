package com.github.yingzhuo.spring.cloud.examples.consumer.cli.config

import feign.{RequestInterceptor, RequestTemplate}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class FeignConfig(@Value("${spring.application.name}") appName: String) {

  @Bean
  def consumerRequestInterceptor(): RequestInterceptor = {
    template: RequestTemplate => template.header("Consumer", appName)
  }

}
