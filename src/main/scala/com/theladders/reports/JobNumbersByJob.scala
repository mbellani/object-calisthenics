package com.theladders.reports

import com.theladders.jobapplication.JobApplication

import scala.collection.mutable.ArrayBuffer

class JobNumbersByJob extends JobApplicationReport {
  val field = new GroupByField("Jobs")

  override def apply(application: JobApplication): Unit = {
    application.displayJob(field)
  }

  override protected def rows: ArrayBuffer[Row] = {
    val row = new Row
    row.write(field)
    ArrayBuffer[Row](row)
  }
}

