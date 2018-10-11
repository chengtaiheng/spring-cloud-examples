package com.github.yingzhuo.spring.cloud.examples.provider.dao

import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.{JpaRepository, Query}
import org.springframework.data.repository.query.Param

trait KeeperDao extends JpaRepository[Keeper, String] with KeeperExtDao {

  def findByName(name: String): Keeper

  @Query("select count(k.id) <> 0 from Keeper k where k.name = :name")
  def existsByName(@Param("name") name: String): Boolean

}

trait KeeperExtDao

class KeeperDaoImpl(em: EntityManager) extends KeeperExtDao
