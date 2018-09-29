package com.github.yingzhuo.spring.cloud.examples.provider.controller

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import com.github.yingzhuo.spring.cloud.examples.provider.dao.CatDao
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/cat"))
class CatController(catDao: CatDao) {

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Cat = catDao.findById(id).orElseGet(() => null)

  @GetMapping(Array("/name/{name}"))
  def findByName(@PathVariable("name") name: String): Cat = catDao.findByName(name)

}
