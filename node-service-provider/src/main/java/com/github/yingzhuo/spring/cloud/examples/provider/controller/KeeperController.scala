package com.github.yingzhuo.spring.cloud.examples.provider.controller

import java.util

import com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException
import com.github.yingzhuo.spring.cloud.examples.common.util.uuid
import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import com.github.yingzhuo.spring.cloud.examples.provider.dao.KeeperDao
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/keeper"))
class KeeperController(keeperDao: KeeperDao) {

  val log = Logger(getClass)

  @GetMapping
  def findAll(): util.List[Keeper] = keeperDao.findAll()

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Keeper = keeperDao.findById(id).orElse(null)

  @PostMapping
  def createKeeper(@RequestParam("name") name: String): Keeper = {

    log.debug("name={}", name)

    if (keeperDao.existsByName(name)) {
      throw BusinessException(s"'$name'已经被占用")
    }

    val keeper = new Keeper
    keeper.id = uuid()
    keeper.name = name

    keeperDao.save(keeper)
  }

}
