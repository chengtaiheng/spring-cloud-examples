package com.github.yingzhuo.spring.cloud.examples.entity.pet

import java.util.Date

import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import javax.persistence._
import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}

import scala.beans.BeanProperty

object Cat {
}

/**
  * 猫
  */
@Entity
@Table(name = "t_cat")
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

  @CreatedDate
  @Column(name = "created_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var createdTime: Date = _

  @LastModifiedDate
  @Column(name = "last_modified_time")
  @Temporal(TemporalType.TIMESTAMP)
  @BeanProperty
  var lastModifiedTime: Date = _

  @ManyToOne
  @JoinColumn(name = "keeper_id", referencedColumnName = "id", foreignKey = new ForeignKey(value = ConstraintMode.NO_CONSTRAINT, name = "none"))
  var keeper: Keeper = _

}
