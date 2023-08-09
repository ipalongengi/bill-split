package models

sealed trait Consumer
case object Shared extends Consumer
case class Individual(name: String) extends Consumer
case class Group(name: String, individuals: List[Individual]) extends Consumer

object Consumer {
  def isShared(x: Consumer): Boolean = x match {
    case Shared => true
    case _ => false
  }

  def isGroup(x: Consumer): Boolean = x match {
    case _: Group => true
    case _ => false
  }
}