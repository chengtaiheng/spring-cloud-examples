package com.github.yingzhuo.spring.cloud.examples.entity.pet

import java.util.Date

import javax.persistence._
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import scala.beans.BeanProperty

object Cat {
}

/**
  * 猫
  */
@Entity
@Table(name = "t_cat")
@EntityListeners(Array(classOf[AuditingEntityListener]))
class Cat extends Pet {

  @Id
  @Column(name = "id")
  @BeanProperty
  var id: String = _

  @Column(name = "name")
  @BeanProperty
  var name: String = _

  @Enumerated(EnumType.STRING)
  @Column(name = "sex")
  @BeanProperty
  var sex: Sex = _

  @Column(name = "keeper")
  @BeanProperty
  var keeper: String = _

  @Column(name = "created_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var createdTime: Date = _

  @Column(name = "last_modified_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var lastModifiedTime: Date = _

}
