package com.theladders.jobapplication

import com.theladders.job.Job
import com.theladders.jobseeker.JobSeeker
import com.theladders.reports.{Format, Row, Report}

object ApplicationTracker {
  private val jobApplications = new JobApplications()

  def track(application: JobApplication): Unit = {
    jobApplications.add(application)
  }

  def export(report: Report[JobApplication], format:Format): Unit = {
    jobApplications.map(report.apply(_))
    report.print(format)
  }

  def hasApplicationWith(jobSeeker: JobSeeker, job: Job): Boolean = {
    jobApplications.contains(JobApplication(jobSeeker, job))
  }
}
