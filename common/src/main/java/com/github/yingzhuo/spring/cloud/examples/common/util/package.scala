package com.github.yingzhuo.spring.cloud.examples.common

import java.util.UUID

package object util {

  object uuid {
    def apply(): String = UUID.randomUUID().toString.replaceAll("-", "")
  }

}
