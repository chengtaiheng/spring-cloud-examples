package com.github.yingzhuo.spring.cloud.examples.consumer.action

import com.github.yingzhuo.spring.cloud.examples.consumer.cli.CatClient
import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/cat"))
class CatAction(catClient: CatClient) {

  @RequestMapping(Array("/{id}"))
  def findOne(@PathVariable("id") id: String): Cat = {
    catClient.findOne(id)
  }

  @RequestMapping(Array("/{name}"))
  def findByName(@PathVariable("name") name: String): Cat = {
    catClient.findByName(name)
  }

}
