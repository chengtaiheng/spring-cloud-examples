package com.github.yingzhuo.spring.cloud.examples.provider

import java.util.{UUID => JavaUUID}

package object util {

  object UUID {
    def apply(): String = JavaUUID.randomUUID().toString.replaceAll("-", "")
  }

}
