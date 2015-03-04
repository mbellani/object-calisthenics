package com.theladders.resume

import com.theladders.common.StringLike

class ResumeContent(override protected val value: String) extends StringLike(value)

class ResumeTitle(value: String) extends StringLike(value)

class ResumeDetails(protected val resumeTitle: ResumeTitle, protected val resumeContent: ResumeContent) {

  def hasTitle(title: ResumeTitle): Boolean = {
    title == resumeTitle
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[ResumeDetails]
    that != null && this.resumeTitle == that.resumeTitle && this.resumeContent == that.resumeContent
  }
}

object ResumeDetails {
  def apply(resumeTitle: ResumeTitle, resumeContent: ResumeContent): ResumeDetails = {
    new ResumeDetails(resumeTitle, resumeContent)
  }
}

object ResumeTitle {
  def apply(content: String) = new ResumeTitle(content)
}

object ResumeContent {
  def apply(content: String) = new ResumeContent(content)
}
