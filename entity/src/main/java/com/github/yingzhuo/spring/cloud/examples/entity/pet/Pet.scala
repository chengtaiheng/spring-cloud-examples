package com.github.yingzhuo.spring.cloud.examples.entity.pet

import javax.persistence.{Column, Entity, Id, Table}

import scala.beans.BeanProperty

/**
  * 宠物
  */
sealed trait Pet extends Serializable

object Cat {

}

@Entity
@Table(name = "t_cat")
class Cat extends Pet {

  @Id
  @BeanProperty
  var id: String = _

  @Column(name = "name")
  @BeanProperty
  var name: String = _

  @Column(name = "keeper")
  @BeanProperty
  var keeper: String = _

}
