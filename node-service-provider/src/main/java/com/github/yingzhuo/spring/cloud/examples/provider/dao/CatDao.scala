package com.github.yingzhuo.spring.cloud.examples.provider.dao

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param

sealed trait CatDao extends JpaRepository[Cat, String] with CatExtDao {

  def findByName(name: String): Cat

  @Query("select count(c.id) <> 0 from Cat c where c.name = :name")
  def existsByName(@Param("name") name: String): Boolean

  @Query("select count(k.id) <> 0 from Cat c left join c.keeper k where k.id = :keeperId")
  def isKeeperIdUsed(@Param("keeperId") keeperId: String): Boolean

}

sealed trait CatExtDao

class CatDaoImpl(em: EntityManager) extends CatExtDao