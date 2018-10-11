package com.github.yingzhuo.spring.cloud.examples.consumer.action

import com.github.yingzhuo.spring.cloud.examples.common.json.Json
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.web.bind.annotation.{GetMapping, RestController}

// 为了验证spring-cloud-config

@RestController
class AuthorAction @Autowired()(@Value("${author:unknown}") authorProperty: String) {

  @GetMapping(Array("/author"))
  def author(): Json = {
    Json()
      .add(("author", "authorProperty"))
  }

}
