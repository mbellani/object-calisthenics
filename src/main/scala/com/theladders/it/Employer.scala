package com.theladders.it

import com.theladders.common.Name
import com.theladders.job._
import com.theladders.jobapplication.{JReq, ATS, JobApplicationSystem}
import com.theladders.reports.{Row, Field}

class Employer(private val name: Name) {
  def post(destination: JobApplicationSystem, jobDetails: JobDetails): Unit = {
    val job = Job(jobDetails, this)
    destination.post(job)
  }

  def listings: Jobs = {
    Jobs.merge(ATS.listings(this), JReq.listings(this))
  }

  def matches(name: Name): Boolean = {
    this.name.equals(name)
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[Employer]
    that != null && name == that.name
  }

  def display(field: Field): Unit = {
    field.write(name.toString())
  }
}

object Employer {
  def apply(name: Name): Employer = {
    new Employer(name)
  }
}