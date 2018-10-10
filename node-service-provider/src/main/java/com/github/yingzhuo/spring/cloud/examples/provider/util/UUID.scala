package com.github.yingzhuo.spring.cloud.examples.provider.util

object UUID {

  def apply(): String = java.util.UUID.randomUUID().toString.replaceAll("-", "")

}
