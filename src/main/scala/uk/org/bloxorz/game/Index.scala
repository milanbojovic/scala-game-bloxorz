package uk.org.bloxorz.game

class Index(val i: Int, val j: Int) {
  def isValid: Boolean = {i>=0 && j>=0}
  override def toString: String  = s"($i,$j)"
}