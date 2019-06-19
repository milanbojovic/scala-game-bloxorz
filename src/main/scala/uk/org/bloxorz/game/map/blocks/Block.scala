package uk.org.bloxorz.game.map.blocks

import com.typesafe.scalalogging.LazyLogging

class Block(var orientation: Orientation.Value, var bricks: List[Point]) extends LazyLogging {

  def allPositions(): Set[Block] = {
    var res: Set[Block] = Set()
    for (direction <- Direction.values) res = res + new Block(calcOrientation(direction), calcBricksList(direction))
    res
  }

  def moveBlock(dir: Direction.Value): Block = {
    new Block(calcOrientation(dir), calcBricksList(dir))
  }

  private def calcOrientation(dir: Direction.Value): Orientation.Value = orientation match {
    case Orientation.Vertical   => dir match {
      case Direction.UP     =>  Orientation.NorthSouth
      case Direction.DOWN   =>  Orientation.NorthSouth
      case Direction.LEFT   =>  Orientation.EastWest
      case Direction.RIGHT  =>  Orientation.EastWest
    }
    case Orientation.EastWest   => dir match {
      case Direction.UP     =>  Orientation.EastWest
      case Direction.DOWN   =>  Orientation.EastWest
      case Direction.LEFT   =>  Orientation.Vertical
      case Direction.RIGHT  =>  Orientation.Vertical
    }
    case Orientation.NorthSouth => dir match {
      case Direction.UP     =>  Orientation.Vertical
      case Direction.DOWN   =>  Orientation.Vertical
      case Direction.LEFT   =>  Orientation.NorthSouth
      case Direction.RIGHT  =>  Orientation.NorthSouth
    }
  }

  private def calcBricksList(dir: Direction.Value): List[Point] = orientation match {
    case Orientation.Vertical   => dir match {
      case _ =>  calcNewIndex(calcNewIndex(bricks.head, dir), dir) :: calcNewIndex(bricks.head, dir) ::  Nil
    }
    case Orientation.EastWest   => dir match {
      case Direction.UP   | Direction.DOWN    =>  calcNewIndex(bricks.head, dir) :: calcNewIndex(bricks.tail.head, dir) :: Nil
      case Direction.LEFT                     =>  calcNewIndex(findLeftIndex(bricks), dir) :: Nil
      case Direction.RIGHT                    =>  calcNewIndex(findRightIndex(bricks), dir) :: Nil

    }
    case Orientation.NorthSouth => dir match {
      case Direction.LEFT | Direction.RIGHT   =>  calcNewIndex(bricks.head, dir) :: calcNewIndex(bricks.tail.head, dir) :: Nil
      case Direction.UP                         =>  calcNewIndex(findTopIndex(bricks), dir) :: Nil
      case Direction.DOWN                       =>  calcNewIndex(findBottomIndex(bricks), dir) :: Nil
    }
  }

  private def calcNewIndex(ind: Point, direction: Direction.Value): Point =  direction match {
    case Direction.UP     =>  new Point(ind.i - 1, ind.j)
    case Direction.DOWN   =>  new Point(ind.i + 1, ind.j)
    case Direction.LEFT   =>  new Point(ind.i, ind.j - 1)
    case Direction.RIGHT  =>  new Point(ind.i, ind.j + 1)
  }

  private def findLeftIndex(list: List[Point]) : Point = {
    if (list.head.j < list.tail.head.j) list.head
    else list.tail.head
  }

  private def findRightIndex(list: List[Point]) : Point = {
    if (list.head.j < list.tail.head.j) list.tail.head
    else list.head
  }

  private def findTopIndex(list: List[Point]) : Point = {
    if (list.head.i < list.tail.head.i) list.head
    else list.tail.head
  }

  private def findBottomIndex(list: List[Point]) : Point = {
    if (list.head.i < list.tail.head.i) list.tail.head
    else list.head
  }

  def canEqual(a: Any): Boolean = a.isInstanceOf[Block]

  override def equals(blk: Any): Boolean = blk match {
    case blk: Block =>  blk.canEqual(this)  && this.hashCode == blk.hashCode
    case _ => false
  }

  override def hashCode:Int = {
    val prime = 31
    var result = 1
    result = prime * result + orientation.id
    result = prime * result + bricks.foldLeft(0)(_.hashCode() + _.hashCode)
    result
  }

  override def toString: String = {
    s"BLK {${orientation.toString}, Bricks: ${bricks.reverse.mkString("[", ",", "]}")}"
  }
}
