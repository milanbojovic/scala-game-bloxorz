package uk.org.bloxorz.game

class Block(var orientation: Orientation.Value, var bricks: List[Index]) {

  def changeOrientation(dir: Direction.Value): Orientation.Value = orientation match {
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

  def calcBricksList(dir: Direction.Value): List[Index] = orientation match {
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

  def calcNewIndex(ind: Index, direction: Direction.Value): Index =  direction match {
    case Direction.UP     =>  new Index(ind.i - 1, ind.j)
    case Direction.DOWN   =>  new Index(ind.i + 1, ind.j)
    case Direction.LEFT   =>  new Index(ind.i, ind.j - 1)
    case Direction.RIGHT  =>  new Index(ind.i, ind.j + 1)
  }

  private def findLeftIndex(list: List[Index]) : Index = {
    if (list.head.j < list.tail.head.j) list.head
    else list.tail.head
  }

  private def findRightIndex(list: List[Index]) : Index = {
    if (list.head.j < list.tail.head.j) list.tail.head
    else list.head
  }

  private def findTopIndex(list: List[Index]) : Index = {
    if (list.head.i < list.tail.head.i) list.head
    else list.tail.head
  }

  private def findBottomIndex(list: List[Index]) : Index = {
    if (list.head.i < list.tail.head.i) list.tail.head
    else list.head
  }

  override def toString: String = {
    "Orientation: ".concat(orientation.toString).concat(", Bricks: ").concat(bricks.mkString("[", ",", "]")).toString()
  }
}
