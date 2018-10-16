package com.github.yingzhuo.spring.cloud.examples.common.json

import scala.collection.mutable

case class Json(error: String = null, payload: mutable.Map[String, Any] = mutable.Map()) {

  def +(pair: (String, Any)): Json = {
    payload += pair
    this
  }

  def add(k: String, v: Any): Json = this + (k, v)

}
