package models

sealed trait Consumer
case object Shared extends Consumer
case class Individual(name: String) extends Consumer

object Consumer {
  def isShared(x: Consumer): Boolean = x match {
    case Shared => true
    case _ => false
  }
}
