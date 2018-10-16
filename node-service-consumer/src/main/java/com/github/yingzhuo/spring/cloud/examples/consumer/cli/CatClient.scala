package com.github.yingzhuo.spring.cloud.examples.consumer.cli

import com.github.yingzhuo.spring.cloud.examples.consumer.cli.config.FeignConfig
import com.github.yingzhuo.spring.cloud.examples.entity.pet.{Cat, Sex}
import com.typesafe.scalalogging.Logger
import feign.hystrix.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestParam}

@FeignClient(
  name = "node-service-provider",
  path = "/cat",
  fallbackFactory = classOf[CatClient.Fallback],
  configuration = Array(classOf[FeignConfig])
)
trait CatClient {

  @GetMapping(Array("/{id}"))
  def findOne(@PathVariable("id") id: String): Cat

  @GetMapping(Array("/{name}"))
  def findByName(@PathVariable("name") name: String): Cat

  @PostMapping
  def createNewCat(@RequestParam("name") name: String,
                   @RequestParam("sex") sex: Sex,
                   @RequestParam("keeperId") keeperId: String): Cat

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

        override def createNewCat(name: String, sex: Sex, keeperId: String): Cat = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          null
        }
      }
    }
  }

}
