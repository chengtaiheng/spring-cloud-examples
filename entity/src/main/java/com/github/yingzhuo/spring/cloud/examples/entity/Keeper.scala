package com.github.yingzhuo.spring.cloud.examples.entity

import java.util.Date

import javax.persistence._
import org.springframework.data.annotation.CreatedDate

import scala.beans.BeanProperty

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

  @CreatedDate
  @Column(name = "created_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var createdTime: Date = _

}
