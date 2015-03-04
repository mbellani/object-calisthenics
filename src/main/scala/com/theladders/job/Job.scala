package com.theladders.job

import com.theladders.common._
import com.theladders.it.Employer
import com.theladders.reports.{Row, Field}

class Job(protected val jobDetails: JobDetails, protected val postedBy: Employer) extends DefaultResumeRule {
  checkNotNull(jobDetails, "Must provide a job title to create a Job")
  checkNotNull(postedBy, "must have an employer for posting a job")

  def this(job: Job) = {
    this(job.jobDetails, job.postedBy)
  }

  def isPostedBy(employer: Employer): Boolean = {
    employer != null && this.postedBy == employer
  }

  def hasTitle(jobTitle: JobTitle): Boolean = {
    jobDetails.hasTitle(jobTitle)
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[Job]
    that != null && jobDetails.equals(that.jobDetails) && postedBy.equals(that.postedBy)
  }

  override def hashCode(): Int = {
    val state = Seq(jobDetails, postedBy)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  def display(field: Field): Unit = {
    jobDetails.display(field)
    field.write("--")
    postedBy.display(field)
  }
}

object Job {
  def apply(jobDetails: JobDetails, postedBy: Employer): Job = {
    new Job(jobDetails, postedBy)
  }
}











