package com.theladders.job

import com.theladders.employer.Employer

import scala.collection.mutable.ArrayBuffer
import com.theladders.common.checkNotNull

class Jobs(private val jobList: ArrayBuffer[Job] = ArrayBuffer()) {
  checkNotNull(jobList, "must provide at-least empty job list")

  def add(job: Job) = {
    checkNotNull(job, "Cannot add a null job to collection")
    jobList += job
  }

  def contains(job: Job): Boolean = {
    jobList.contains(job)
  }

  def listings(employer: Employer): Jobs = {
    new Jobs(jobList.filter(_.isPostedBy(employer)))
  }

  def find(jobTitle: JobTitle): Jobs = {
    new Jobs(jobList.filter(_.hasTitle(jobTitle)))
  }

  def count(employer: Employer): Int = {
    listings(employer).size()
  }

  def reset: Unit = {
    jobList.clear()
  }

  def size(): Int = {
    jobList.size
  }
}

object Jobs {
  def merge(jobs1: Jobs, jobs2: Jobs): Jobs = {
    new Jobs(jobs1.jobList ++ jobs2.jobList)
  }
}