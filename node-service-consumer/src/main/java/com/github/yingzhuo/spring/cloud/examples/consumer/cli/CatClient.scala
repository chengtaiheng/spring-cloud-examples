package com.github.yingzhuo.spring.cloud.examples.consumer.cli

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import com.typesafe.scalalogging.Logger
import feign.hystrix.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{GetMapping, PathVariable}

@FeignClient(
  name = "node-service-provider",
  path = "/cat",
  fallbackFactory = classOf[CatClient.Fallback]
)
trait CatClient {

  @GetMapping(Array("/{id}"))
  def findOne(@PathVariable("id") id: String): Cat

  @GetMapping(Array("/{name}"))
  def findByName(@PathVariable("name") name: String): Cat

}

object CatClient {

  @Component
  class Fallback extends FallbackFactory[CatClient] {
    override def create(cause: Throwable): CatClient = {
      val log = Logger(classOf[Fallback])

      new CatClient {

        override def findOne(id: String): Cat = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          null
        }

        override def findByName(name: String): Cat = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          null
        }
      }
    }
  }

}