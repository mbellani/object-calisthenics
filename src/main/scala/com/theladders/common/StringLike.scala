package com.theladders.common

abstract class StringLike(protected val value: String) {
  checkNotNull(value, "cannot have null value for a string like instance")

  override def toString() = {
    value
  }

  override def equals(other: Any) = {
    val that = other.asInstanceOf[StringLike]
    that != null && this.value == that.value
  }
}
