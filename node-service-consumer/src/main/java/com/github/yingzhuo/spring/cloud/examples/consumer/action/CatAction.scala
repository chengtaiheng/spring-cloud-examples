package com.github.yingzhuo.spring.cloud.examples.consumer.action

import com.github.yingzhuo.spring.cloud.examples.common.json.Json
import com.github.yingzhuo.spring.cloud.examples.consumer.cli.CatClient
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/cat"))
class CatAction(catClient: CatClient) {

  val log = Logger(getClass)

  @RequestMapping(Array("/{id}"))
  def findOne(@PathVariable("id") id: String): Json = {
    val cat = catClient.findOne(id)

    Json()
      .add(("cat", cat))
  }

  @RequestMapping(Array("/name/{name}"))
  def findByName(@PathVariable("name") name: String): Json = {
    val cat = catClient.findByName(name)

    Json()
      .add(("cat", cat))
  }

}
