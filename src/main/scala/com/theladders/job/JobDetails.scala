package com.theladders.job

import com.theladders.common.{StringLike, checkNotNull}
import com.theladders.reports.{Row, Field}

case class JobDetails(private val jobTitle: JobTitle, private val jobDescription: JobDescription) {
  checkNotNull(jobTitle, "must have a job title to create job details")
  checkNotNull(jobDescription, "must have a job description to create job details")

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[JobDetails]
    that != null && jobTitle.equals(that.jobTitle) && jobDescription.equals(jobDescription)
  }

  def hasTitle(jobTitle: JobTitle): Boolean = {
    this.jobTitle == jobTitle
  }

  def display(field: Field) = {
    field.write(jobTitle.toString())
  }
}

case class JobTitle(override val value: String) extends StringLike(value)

case class JobDescription(override val value: String) extends StringLike(value)
