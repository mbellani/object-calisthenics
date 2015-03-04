package com.theladders.it

import com.theladders.common.Name
import com.theladders.job._
import com.theladders.jobapplication.ApplicationTracker
import com.theladders.jobseeker.JobSeeker
import com.theladders.resume.{Resume, ResumeContent, ResumeDetails, ResumeTitle}
import org.scalatest.{Matchers, FlatSpec}

class JobSeekerSpec extends FlatSpec with Matchers {
  "JobSeeker" should "be able to post a resume" in {
    val jobSeeker = JobSeeker(Name("rowlf"))
    val resumeDetails = ResumeDetails(ResumeTitle("Software Engineer"), ResumeContent("Writes code"))
    val posted = jobSeeker.post(resumeDetails)
    assert(jobSeeker.hasResume(posted))
  }

  "JobSeeker" should "be allowed to save a job for later viewing" in {
    val rowlf = new JobSeeker(Name("rowlf"))
    val jobDetails = JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code"))
    val job = new Job(jobDetails, Employer(Name("TheLadders")))

    rowlf.save(job)
    assert(rowlf.hasSaved(job))
  }

  "JobSeeker" should "be allowed to apply for ATS Job without a resume" in {
    val jobSeeker = JobSeeker(Name("Rowlf"))
    val jobDetails = JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code"))
    val job = new Job(jobDetails, Employer(Name("TheLadders")))

    jobSeeker.apply(job, None)
    assert(ApplicationTracker.hasApplicationWith(jobSeeker,job))
  }

  "JobSeeker" should "not be allowed to apply for JReqJob without resume" in {
    val jobSeeker = JobSeeker(Name("Rowlf"))
    val jobDetails = JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code"))
    val job = new Job(jobDetails, Employer(Name("TheLadders"))) with ResumeRequired

    a[IllegalArgumentException] should be thrownBy {
      jobSeeker.apply(job, None)
    }
  }

  "JobSeeker" should "be allowed to apply for JReq job with resume" in {
    val jobSeeker = JobSeeker(Name("Rowlf"))
    val job = new Job(JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code")),
      Employer(Name("TheLadders"))) with ResumeRequired
    val resume = Resume(ResumeDetails(ResumeTitle("Software Engineer"), ResumeContent("Grunt")), jobSeeker)

    jobSeeker.apply(job, Some(resume))
    assert(ApplicationTracker.hasApplicationWith(jobSeeker,job))
  }

  "JobSeeker" should "only be allowed to apply with their own resume" in {
    val rowlf = JobSeeker(Name("Rowlf"))
    val kermit = JobSeeker(Name("Kermit"))
    val kermitResume = kermit.post(ResumeDetails(ResumeTitle("Software Engineer"), ResumeContent("Grunt")))
    val jobDetails = JobDetails(JobTitle("Sr Software Engineer"), JobDescription("Code"))
    val job = new Job(jobDetails, Employer(Name("TheLadders"))) with ResumeRequired

    a[IllegalArgumentException] should be thrownBy {
      rowlf.apply(job, Some(kermitResume))
    }
  }
}