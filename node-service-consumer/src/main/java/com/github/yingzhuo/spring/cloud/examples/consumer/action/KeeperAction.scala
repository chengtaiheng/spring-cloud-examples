package com.github.yingzhuo.spring.cloud.examples.consumer.action

import com.github.yingzhuo.spring.cloud.examples.common.json.Json
import com.github.yingzhuo.spring.cloud.examples.consumer.cli.KeeperClient
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/keeper"))
class KeeperAction(keeperClient: KeeperClient) {

  val log = Logger(getClass)

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Json = {
    log.debug("id={}", id)

    val keeper = keeperClient.findById(id)

    Json()
      .add("keeper", keeper)
  }

  @GetMapping
  def findAll(): Json = {
    val keepers = keeperClient.findAll()

    Json()
      .add("keepers", keepers)
  }

  @PostMapping
  def createKeeper(name: String): Json = {
    log.debug("name={}", name)
    val keeper = keeperClient.createKeeper(name)

    Json()
      .add("keeper", keeper)
  }

}
