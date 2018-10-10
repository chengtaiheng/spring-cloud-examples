package com.github.yingzhuo.spring.cloud.examples.provider.controller

import java.util

import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import com.github.yingzhuo.spring.cloud.examples.provider.dao.KeeperDao
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/keeper"))
class KeeperController(keeperDao: KeeperDao) {

  @GetMapping
  def findAll(): util.List[Keeper] = keeperDao.findAll()

  @GetMapping(Array("/{id}"))
  def findById(@PathVariable("id") id: String): Keeper = keeperDao.findById(id).orElse(null)

}
