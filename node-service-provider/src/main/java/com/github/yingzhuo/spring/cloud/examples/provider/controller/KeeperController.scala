package com.github.yingzhuo.spring.cloud.examples.provider.controller

import java.util

import com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException
import com.github.yingzhuo.spring.cloud.examples.common.util.uuid
import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import com.github.yingzhuo.spring.cloud.examples.provider.dao.{CatDao, KeeperDao}
import com.typesafe.scalalogging.Logger
import org.springframework.web.bind.annotation._

import scala.util.Try

@RestController
@RequestMapping(Array("/keeper"))
class KeeperController(keeperDao: KeeperDao, catDao: CatDao) {

  val log = Logger(getClass)

  @GetMapping
  def findAll(): util.List[Keeper] = keeperDao.findAll()

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Keeper = keeperDao.findById(id).orElse(null)

  @PostMapping
  def create(@RequestParam("name") name: String): Keeper = {

    log.debug("name={}", name)

    if (keeperDao.existsByName(name)) {
      throw BusinessException(s"'$name'已经被占用")
    }

    val keeper = new Keeper
    keeper.id = uuid()
    keeper.name = name

    keeperDao.save(keeper)
  }

  @PutMapping
  def rename(@RequestParam("id") id: String, @RequestParam("name") name: String): Unit = {

    val optional = keeperDao.findById(id)

    optional.ifPresent {
      case x: Keeper =>
        x.name = name
        keeperDao.save(x)
    }
  }

  @DeleteMapping(Array("/{keeperId}"))
  def delete(@PathVariable("keeperId") keeperId: String): Unit = {

    if (catDao.isKeeperIdUsed(keeperId)) {

      throw BusinessException(s"keeperId=${keeperId}已被使用")
    }
    else {
      Try(keeperDao.deleteById(keeperId)) match {
        case scala.util.Failure(_) => log.debug("id为{}的Keeper不存在", keeperId)
        case _ =>
      }
    }
  }

}
