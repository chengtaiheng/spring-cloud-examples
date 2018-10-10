package com.github.yingzhuo.spring.cloud.examples.provider.controller

import com.github.yingzhuo.spring.cloud.examples.entity.pet.{Cat, Sex}
import com.github.yingzhuo.spring.cloud.examples.provider.dao.{CatDao, KeeperDao}
import com.github.yingzhuo.spring.cloud.examples.provider.util.UUID
import org.springframework.cache.annotation.Cacheable
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
  def createNewCat(@RequestParam("name") name: String,
                   @RequestParam("sex") sex: Sex,
                   @RequestParam(value = "keeperId", required = false) keeperId: String): Cat = {

    val keeper =
      if (keeperId != null)
        keeperDao.findById(keeperId).orElse(null)
      else
        null

    val cat = new Cat
    cat.id = UUID()
    cat.sex = sex
    cat.name = name
    cat.keeper = keeper
    catDao.save(cat)
  }

}
