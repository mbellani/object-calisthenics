package com.theladders.resume

import scala.collection.mutable.ArrayBuffer
import com.theladders.common.checkNotNull

class Resumes {
  private val resumeList = ArrayBuffer[Resume]()

  def add(resume: Resume): Unit = {
    checkNotNull(resume, "cannot create a null resume")
    resumeList += resume
  }

  def contains(resume: Resume): Boolean = {
    resumeList.contains(resume)
  }

}
