package com.theladders.common

class Name(override protected val value: String) extends StringLike(value) {
}

object Name {
  def apply(name: String): Name = {
    new Name(name)
  }
}