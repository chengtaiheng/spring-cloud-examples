package com.github.yingzhuo.spring.cloud.examples.provider.entitylistener

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import javax.persistence.PostPersist

class CatListener {

  @PostPersist
  def afterSave(cat: Cat): Unit = {

    println("---")
    println("监听器")
    println("---")

  }

}
