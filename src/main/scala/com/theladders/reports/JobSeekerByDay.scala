package com.theladders.reports

import com.theladders.jobapplication.JobApplication
import org.joda.time.LocalDate

import scala.collection.mutable.ArrayBuffer

class JobSeekerByDay(day: LocalDate) extends JobApplicationReport {
  private val rowList = ArrayBuffer[Row]()

  override def apply(application: JobApplication): Unit = {
    if (application.appliedOn(day)) {
      val row = new Row
      row.write(jobSeeker(application))
      row.write(job(application))
      rowList += row
    }
  }
  override protected def rows: ArrayBuffer[Row] = rowList
}