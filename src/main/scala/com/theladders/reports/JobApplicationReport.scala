package com.theladders.reports

import com.theladders.jobapplication.JobApplication

import scala.collection.mutable.ArrayBuffer

class JobApplicationReport extends Report[JobApplication] {
  private val rowList = ArrayBuffer[Row]()

  override def apply(application: JobApplication): Unit = {
    val row = new Row
    row.write(applicationDate(application))
    row.write(jobSeeker(application))
    row.write(job(application))
    rowList += row
  }

  protected def applicationDate(application: JobApplication): Field = {
    val field = Field("Application Date")
    application.displayApplicationDate(field)
    field
  }

  protected def jobSeeker(application: JobApplication): Field = {
    val field = Field("Application Date")
    application.displayJobSeeker(field)
    field
  }

  protected def job(application: JobApplication): Field = {
    val field = Field("Job")
    application.displayJob(field)
    field
  }

  override protected def rows: ArrayBuffer[Row] = rowList
}
