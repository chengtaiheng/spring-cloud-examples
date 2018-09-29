package com.github.yingzhuo.spring.cloud.examples.consumer.cli

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.{GetMapping, PathVariable}

@FeignClient(
  name = "node-service-provider",
  path = "/cat"
)
trait CatClient {

  @GetMapping(Array("/{id}"))
  def findOne(@PathVariable("id") id: String): Cat

  @GetMapping(Array("/{name}"))
  def findByName(@PathVariable("name") name: String): Cat

}
