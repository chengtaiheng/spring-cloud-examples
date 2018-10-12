package com.github.yingzhuo.spring.cloud.examples.consumer.action

import com.github.yingzhuo.spring.cloud.examples.common.json.Json
import com.github.yingzhuo.spring.cloud.examples.consumer.cli.KeeperClient
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation.{PostMapping, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/keeper"))
class KeeperAction(keeperClient: KeeperClient) {

  val log = Logger(getClass)

  @PostMapping
  def createKeeper(name: String): Json = {
    log.debug("name={}", name)
    val keeper = keeperClient.createKeeper(name)

    Json()
      .add("keeper", keeper)
  }

}
