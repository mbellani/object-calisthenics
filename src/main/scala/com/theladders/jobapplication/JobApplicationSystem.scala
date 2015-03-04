package com.theladders.jobapplication

import com.theladders.employer.Employer
import com.theladders.job._

abstract class JobApplicationSystem {

  private val jobs = new Jobs

  def post(job: Job): Unit = {
    jobs.add(job)
  }

  def count(employer: Employer): Int = {
    jobs.count(employer: Employer)
  }

  def listings(employer: Employer): Jobs = {
    jobs.listings(employer)
  }

  def add(job: Job): Unit = {
    jobs.add(job)
  }

  def reset: Unit = {
    jobs.reset
  }
}

object ATS extends JobApplicationSystem {
  override def post(job: Job) = {
    val newJob = new Job(job)
    super.post(newJob)
  }
}

object JReq extends JobApplicationSystem {
  override def post(job: Job) = {
    val newJob = new Job(job) with ResumeRequired
    super.post(newJob)
  }
}


