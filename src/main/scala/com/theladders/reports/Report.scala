package com.theladders.reports

import scala.collection.mutable.ArrayBuffer
import scala.collection._

trait Report[T] {
  def apply(t: T): Unit
  protected def rows: ArrayBuffer[Row]

  def print(format: Format): Unit = {
    rows.foreach(format.print(_))
  }
}

class Row {
  private val fields = ArrayBuffer[Field]()

  def map[T](f: (Field) => T): Seq[T] = {
    fields.map(f(_))
  }

  def write(field: Field): Unit = {
    fields += field
  }
}

case class Field(private val name: String) {
  protected val value = new StringBuilder

  def write(s: String): Unit = {
    value ++= s
  }

  override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[Field]
    that != null && this.value.equals(that.value)
  }

  override def hashCode(): Int = {
    val state = Seq(value)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString(): String = {
    value.toString()
  }
}

class GroupByField(private val fieldName: String) extends Field(fieldName) {
  private val counts: mutable.Map[String, Int] = mutable.Map()

  override def write(s: String): Unit = {
    var count = counts.getOrElse(s, 0)
    count += 1
    counts(s) = count
  }

  override def toString(): String = {
    value ++= counts.toString()
    super.toString()
  }
}