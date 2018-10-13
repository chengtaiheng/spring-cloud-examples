package com.github.yingzhuo.spring.cloud.examples.consumer.action

import java.util

import com.github.yingzhuo.spring.cloud.examples.common.json.Json
import com.github.yingzhuo.spring.cloud.examples.consumer.cli.KeeperClient
import com.github.yingzhuo.spring.cloud.examples.entity.pet.Keeper
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/keeper"))
class KeeperAction(keeperClient: KeeperClient) {

  val log = Logger(getClass)

  @GetMapping
  def findAll(): util.List[Keeper] = {
    keeperClient.findAll()
  }

  @PostMapping
  def createKeeper(name: String): Json = {
    log.debug("name={}", name)
    val keeper = keeperClient.createKeeper(name)

    Json()
      .add("keeper", keeper)
  }

}
