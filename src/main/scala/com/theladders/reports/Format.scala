package com.theladders.reports

trait Format {
  def print(row: Row)
}

class CSV extends Format {
  override def print(row: Row): Unit = {
    val output = row.map(_.toString()).mkString(",")
    println(output)
  }
}

object Format {
  def csv: Format = {
    new CSV
  }
}
