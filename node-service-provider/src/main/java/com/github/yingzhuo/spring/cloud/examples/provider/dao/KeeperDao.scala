package com.github.yingzhuo.spring.cloud.examples.provider.dao

import com.github.yingzhuo.spring.cloud.examples.entity.Keeper
import javax.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository

trait KeeperDao extends JpaRepository[Keeper, String] with KeeperExtDao {

  def findByName(name: String): Keeper

}

trait KeeperExtDao

class KeeperDaoImpl(em: EntityManager) extends KeeperExtDao
