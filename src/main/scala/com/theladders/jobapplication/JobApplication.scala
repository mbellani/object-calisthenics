package com.theladders.jobapplication

import com.theladders.job.Job
import com.theladders.jobseeker.JobSeeker
import com.theladders.reports.Field
import org.joda.time.{DateTime, LocalDate}

class JobApplication(private val applicationDate: ApplicationDate, private val applicationDetails: ApplicationDetails) {

  def appliedOn(date: LocalDate): Boolean = {
    this.applicationDate.matches(date)
  }

  def displayJobSeeker(field: Field): Unit = {
    applicationDetails.displayJobSeeker(field)
  }

  def displayApplicationDate(field: Field): Unit = {
    applicationDate.display(field)
  }

  def displayJob(field: Field): Unit = {
    applicationDetails.displayJob(field)
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[JobApplication]
    that != null && applicationDetails.equals(that.applicationDetails)
  }

  override def hashCode(): Int = {
    val state = Seq(applicationDetails)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

case class ApplicationDetails(private val jobSeeker: JobSeeker, private val job: Job) {
  def hasJob(job: Job): Boolean = {
    this.job == job
  }

  def displayJobSeeker(field: Field) = {
    jobSeeker.display(field)
  }

  def displayJob(field: Field): Unit = {
    job.display(field)
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[ApplicationDetails]
    that != null && jobSeeker.equals(that.jobSeeker) && job.equals(that.job)
  }

  override def hashCode(): Int = {
    val state = Seq(jobSeeker, job)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

case class ApplicationDate(private val dateTime: DateTime) {
  def matches(date: LocalDate): Boolean = {
    dateTime.toLocalDate.equals(date)
  }

  def display(field: Field): Unit = {
    field.write(dateTime.toString())
  }
}

object JobApplication {
  def apply(jobSeeker: JobSeeker, job: Job): JobApplication = {
    val details = new ApplicationDetails(jobSeeker, job)
    new JobApplication(ApplicationDate(DateTime.now()), details)
  }
}