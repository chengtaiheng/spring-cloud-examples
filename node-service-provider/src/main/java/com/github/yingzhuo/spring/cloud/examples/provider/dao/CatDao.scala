package com.github.yingzhuo.spring.cloud.examples.provider.dao

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param

trait CatDao extends JpaRepository[Cat, String] with CatExtDao {

  def findByName(name: String): Cat

  @Query("select count(c.id) <> 0 from Cat c where c.name = :name")
  def existsByName(@Param("name") name: String): Boolean

}

trait CatExtDao {
}

class CatDaoImpl(em: EntityManager) extends CatExtDao {
}
