package com.theladders.job

import com.theladders.resume.Resume

trait DefaultResumeRule {
  def apply(resume: Option[Resume]): Unit = {}
}

trait ResumeRequired extends DefaultResumeRule {
  override def apply(resume: Option[Resume]): Unit = {
    if (resume.isEmpty) throw new IllegalArgumentException("Resume is required")
  }
}