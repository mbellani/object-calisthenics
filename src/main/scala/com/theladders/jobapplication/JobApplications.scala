package com.theladders.jobapplication

import com.theladders.common._
import com.theladders.it.Employer
import com.theladders.job.Job
import com.theladders.jobseeker.JobSeeker
import org.joda.time.LocalDate

import scala.collection.mutable.ArrayBuffer

class JobApplications(private val applicationList: ArrayBuffer[JobApplication] = ArrayBuffer()) {
  def contains(application: JobApplication): Boolean = {
    applicationList.contains(application)
  }

  def filterByDate(date: LocalDate): JobApplications = {
    val filtered = applicationList.filter(_.appliedOn(date))
    new JobApplications(filtered)
  }

  def add(jobApplication: JobApplication): Unit = {
    checkNotNull(jobApplication, "cannot add null job application")
    applicationList += jobApplication
  }

  def map[T](f: (JobApplication) => T): Seq[T] = {
    for (jobApplication <- applicationList) yield {
      f(jobApplication)
    }
  }
}
