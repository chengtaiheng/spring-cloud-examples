package com.github.yingzhuo.spring.cloud.examples.provider.controller

import com.github.yingzhuo.spring.cloud.examples.common.exception.BusinessException
import com.github.yingzhuo.spring.cloud.examples.common.util.uuid
import com.github.yingzhuo.spring.cloud.examples.entity.pet.{Cat, Sex}
import com.github.yingzhuo.spring.cloud.examples.provider.dao.{CatDao, KeeperDao}
import org.springframework.cache.annotation.Cacheable
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/cat"))
class CatController(catDao: CatDao, keeperDao: KeeperDao) {

  @GetMapping(Array("/{id}"))
  @Cacheable(cacheNames = Array("cats"), unless = "#result == null")
  def findById(@PathVariable("id") id: String): Cat = catDao.findById(id).orElseGet(() => null)

  @GetMapping(Array("/name/{name}"))
  @Cacheable(cacheNames = Array("cats"), unless = "#result == null")
  def findByName(@PathVariable("name") name: String): Cat = catDao.findByName(name)

  @PostMapping
  @Transactional(propagation = Propagation.REQUIRED)
  def createNewCat(@RequestParam("name") name: String,
                   @RequestParam("sex") sex: Sex,
                   @RequestParam(value = "keeperId", required = false) keeperId: String): Cat = {

    // 不允许重名
    if (catDao.existsByName(name)) {
      throw BusinessException(s"'$name'已经被占用")
    }

    val keeper = keeperId match {
      case null => null
      case x if x.isEmpty => null
      case x => keeperDao.findById(x).orElse(null)
    }

    val cat = new Cat
    cat.id = uuid()
    cat.sex = sex
    cat.name = name
    cat.keeper = keeper
    catDao.saveAndFlush(cat)
  }

  @PutMapping(Array("/{id}"))
  @Transactional(propagation = Propagation.SUPPORTS)
  def update(@PathVariable("id") id: String,
             @RequestParam(value = "name", required = false) name: String,
             @RequestParam(value = "sex", required = false) sex: Sex): Unit = {

    val optional = catDao.findById(id)

    optional.ifPresent {
      case x: Cat =>
        if (name != null) x.name = name
        if (sex != null) x.sex = sex
        catDao.save(x)
    }
  }

}
