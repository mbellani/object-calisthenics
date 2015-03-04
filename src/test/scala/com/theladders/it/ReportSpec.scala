package com.theladders.it

import com.theladders.common.Name
import com.theladders.job.{Job, JobDescription, JobDetails, JobTitle}
import com.theladders.jobapplication.ApplicationTracker
import com.theladders.jobseeker.JobSeeker
import com.theladders.reports.{JobNumbersByJob, JobApplicationReport, Format, JobSeekerByDay}
import org.joda.time.LocalDate
import org.scalatest.{FlatSpec, Matchers}

class ReportSpec extends FlatSpec with Matchers {
  def fixture = new {
    def execute() {
      val rowlf = JobSeeker(Name("Rowlf"))
      val kermit = JobSeeker(Name("Kermit"))
      val jobDetails = JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code"))
      val job = new Job(jobDetails, Employer(Name("TheLadders")))
      rowlf.apply(job, None)
      kermit.apply(job, None)
    }
  }


  "TheLadders" should "be able to get a report of which jobseekers have applied to jobs on any given day" in {
    fixture.execute()

    val jobSeekerByDay = new JobSeekerByDay(LocalDate.now())
    ApplicationTracker.export(jobSeekerByDay, Format.csv)
  }

  "TheLadders" should "be able to get the job application report in CSV Format" in {
    fixture.execute()

    ApplicationTracker.export(new JobApplicationReport(), Format.csv)
  }

  "TheLadders" should "be able to see aggregate job application numbers by job" in {
    fixture.execute()
    ApplicationTracker.export(new JobNumbersByJob, Format.csv)
  }

}
