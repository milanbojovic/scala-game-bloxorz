package uk.org.bloxorz.game.map.blocks

class Point(val i: Int, val j: Int) {
  def canEqual(a: Any) = a.isInstanceOf[Point]

  override def equals(ind: Any): Boolean = ind match {
    case ind: Point =>  ind.canEqual(this)  && this.hashCode == ind.hashCode
    case _          =>  false
  }

  override def hashCode:Int = {
    val prime = 31
    var result = 1
    result = prime * result + i;
    result = prime * result + j
    return result
  }

  override def toString: String = s"($i,$j)"
}