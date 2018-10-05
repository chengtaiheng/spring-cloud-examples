package com.github.yingzhuo.spring.cloud.examples.provider.dao

import com.github.yingzhuo.spring.cloud.examples.entity.pet.Cat
import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository

trait CatDao extends JpaRepository[Cat, String] with CatExtDao {

  def findByName(name: String): Cat

}

trait CatExtDao

class CatDaoImpl(em: EntityManager) extends CatExtDao
