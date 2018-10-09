package com.github.yingzhuo.spring.cloud.examples.consumer.action

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.{GetMapping, RestController}

// 为了验证spring-cloud-config

@RestController
class AuthorAction {

  @Value("${author:unknown}")
  private var authorProperty: String = _

  @GetMapping(Array("/author"))
  def author(): String = authorProperty

}
