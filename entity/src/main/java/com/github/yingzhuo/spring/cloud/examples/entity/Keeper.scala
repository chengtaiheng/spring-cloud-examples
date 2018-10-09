package com.github.yingzhuo.spring.cloud.examples.entity

import javax.persistence.{Column, Entity, Id, Table}

object Keeper {
}

@Entity
@Table(name = "t_keeper")
class Keeper {

  @Id
  @Column(name = "id", length = 32)
  var id: String = _

  @Column(name = "name", length = 20)
  var name: String = _

}
