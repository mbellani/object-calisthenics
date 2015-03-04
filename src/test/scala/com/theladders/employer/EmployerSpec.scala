package com.theladders.employer

import com.theladders.common.Name
import com.theladders.job._
import com.theladders.jobapplication.{JReq, ATS}
import org.scalatest._


class EmployerSpec extends FlatSpec with Matchers {

  "Employers" should "be able to create a job in ATS system" in {
    ATS.reset
    JReq.reset
    val employer = Employer(Name("TheLadders"))
    val jobDetails = JobDetails(JobTitle("Software Engineer"), JobDescription("Code"))
    employer.post(ATS, jobDetails)
    assertResult(1)(ATS.count(employer))
    assertResult(0)(JReq.count(employer))
  }

  "Employers" should "be able to create a job in JReq system" in {
    ATS.reset
    JReq.reset
    val employer = Employer(Name("TheLadders"))
    val jobDetails = JobDetails(JobTitle("Software Engineer"), JobDescription("Code"))
    employer.post(JReq, jobDetails)
    assertResult(0)(ATS.count(employer))
    assertResult(1)(JReq.count(employer))
  }

  "Employers" should "be able to see a listing of the jobs they have posted" in {
    ATS.reset
    JReq.reset
    val employer = Employer(Name("TheLadders"))
    val job1 = JobDetails(JobTitle("Software Engineer"), JobDescription("Code"))
    val job2 = JobDetails(JobTitle("QA Engineer"), JobDescription("Test"))
    employer.post(JReq, job1)
    employer.post(ATS, job2)
    assert(employer.listings.size() == 2)
  }
}
