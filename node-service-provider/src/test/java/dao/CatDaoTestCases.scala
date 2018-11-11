package dao

import com.github.yingzhuo.spring.cloud.examples.provider.ApplicationBoot
import com.github.yingzhuo.spring.cloud.examples.provider.dao.CatDao
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[ApplicationBoot]))
@ActiveProfiles(Array("dev"))
class CatDaoTestCases {

  @Autowired
  var dao: CatDao = _


  @Test
  def test1(): Unit = {

    val f = dao.isKeeperIdUsed("d562e8ebb2514b188b7ce22b5bb281fe")

    println(f)
  }

}
