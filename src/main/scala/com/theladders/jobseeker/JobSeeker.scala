package com.theladders.jobseeker

import com.theladders.common.Name
import com.theladders.job.{Job, Jobs}
import com.theladders.jobapplication.{ApplicationTracker, JobApplication}
import com.theladders.reports.Field
import com.theladders.resume._

class JobSeeker(private val name: Name) {
  private val resumes = new Resumes
  private val savedJobsList = new Jobs

  def post(resumeDetails: ResumeDetails): Resume = {
    val resume = Resume(resumeDetails, this)
    resumes.add(resume)
    resume
  }

  def hasResume(resume: Resume): Boolean = {
    resumes.contains(resume)
  }

  def apply(job: Job, resume: Option[Resume]): Unit = {
    canApplyWith(resume)
    job.apply(resume)
    ApplicationTracker.track(JobApplication(this, job))
  }

  def save(job: Job): Unit = {
    savedJobsList.add(job)
  }

  def hasSaved(job: Job): Boolean = {
    savedJobsList.contains(job)
  }

  private def canApplyWith(resume: Option[Resume]) = {
    resume.foreach(r => if (!r.belongsTo(this)) {
      throw new IllegalArgumentException(s"Resume does not belong to ${this.name}")
    })
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[JobSeeker]
    that != null && that.name == this.name
  }

  def display(field: Field): Unit = {
    field.write(name.toString())
  }
}

object JobSeeker {
  def apply(name: Name): JobSeeker = {
    new JobSeeker(name)
  }
}
