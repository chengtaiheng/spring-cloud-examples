package com.github.yingzhuo.spring.cloud.examples.provider.controller

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import com.github.yingzhuo.spring.cloud.examples.provider.dao.CatDao
import org.springframework.cache.annotation.Cacheable
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/cat"))
class CatController(catDao: CatDao) {

  @GetMapping(Array("/{id}"))
  @Cacheable(cacheNames = Array("cats"), unless = "#result == null")
  def findById(@PathVariable("id") id: String): Cat = catDao.findById(id).orElseGet(() => null)

  @GetMapping(Array("/name/{name}"))
  @Cacheable(cacheNames = Array("cats"), unless = "#result == null")
  def findByName(@PathVariable("name") name: String): Cat = catDao.findByName(name)

}
