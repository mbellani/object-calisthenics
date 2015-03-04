package com.theladders

package object common {
  def checkNotNull(obj: Any, message: String) = {
    if (obj == null) throw new IllegalArgumentException(message)
  }
}
