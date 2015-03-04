package com.theladders.resume

import com.theladders.jobseeker.JobSeeker

class Resume(protected val resumeDetails: ResumeDetails, protected val jobSeeker: JobSeeker) {

  def hasTitle(title: ResumeTitle): Boolean = {
    resumeDetails.hasTitle(title)
  }

  def belongsTo(jobSeeker: JobSeeker): Boolean = {
    this.jobSeeker == jobSeeker
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[Resume]
    that != null && this.resumeDetails == that.resumeDetails && this.jobSeeker == that.jobSeeker
  }
}

object Resume {
  def apply(content: ResumeDetails, jobSeeker: JobSeeker): Resume = {
    new Resume(content, jobSeeker)
  }
}

