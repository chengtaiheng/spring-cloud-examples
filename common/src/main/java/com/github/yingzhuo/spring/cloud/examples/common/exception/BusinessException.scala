package com.github.yingzhuo.spring.cloud.examples.common.exception

class BusinessException(msg: String) extends RuntimeException(msg) with Serializable

object BusinessException {
  def apply(msg: String = null): BusinessException = new BusinessException(msg)
}
