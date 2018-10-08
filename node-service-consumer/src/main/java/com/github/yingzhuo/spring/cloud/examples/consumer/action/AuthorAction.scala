package com.github.yingzhuo.spring.cloud.examples.consumer.action

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class AuthorAction {

  @Value("${author}")
  private var author: String = _

  @GetMapping(Array("/author"))
  def getAuthor(): String = author

}
