package com.github.yingzhuo.spring.cloud.examples.consumer.cli

import java.util

import com.github.yingzhuo.spring.cloud.examples.consumer.cli.KeeperClient.Fallback
import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import com.typesafe.scalalogging.Logger
import feign.hystrix.FallbackFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation._

@FeignClient(
  name = "node-service-provider",
  path = "/keeper",
  fallbackFactory = classOf[Fallback]
)
trait KeeperClient {

  @GetMapping
  def findAll(): util.List[Keeper]

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Keeper

  @PostMapping
  def createKeeper(@RequestParam("name") name: String): Keeper

  @PutMapping
  def rename(@RequestParam("id") id: String, @RequestParam("name") name: String): Unit

  @DeleteMapping(Array("/{keeperId}"))
  def delete(@PathVariable("keeperId") keeperId: String): Unit

}

object KeeperClient {

  @Component
  class Fallback extends FallbackFactory[KeeperClient] {
    override def create(cause: Throwable): KeeperClient = {

      val log = Logger(getClass)

      new KeeperClient {
        override def findAll(): util.List[Keeper] = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          new util.ArrayList
        }

        override def findById(id: String): Keeper = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          null
        }

        override def createKeeper(name: String): Keeper = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
          null
        }

        override def rename(id: String, name: String): Unit = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
        }

        override def delete(keeperId: String): Unit = {
          log.error("fallback !")
          log.error(cause.getMessage, cause)
        }
      }
    }
  }

}
