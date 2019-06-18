package uk.org.bloxorz.util

object OldBlock {


  /*  def findPossiblePositions(matrix: Array[Array[Char]]): Set[Block] = {
    //logger.debug(s"Possible solutions")
    var result = allPositions.filter(this.validatePosition(matrix,_))
    //logger.debug(s"         Possible positions for block: ${this} => ${result.mkString(" *** ")}")
    result
  }*//*  private def allPositions(): Set[Block] = {
    var res: Set[Block] = Set()
    for (direction <- Direction.values) res = res + new Block(calcOrientation(direction), calcBricksList(direction))
    res
  }*//*  def validatePosition(matrix: Array[Array[Char]], block: Block): Boolean = block.orientation match {
    case Orientation.Vertical => {
      //logger.debug("Validate block:")
      valiadteBrick(matrix, block.bricks.head, block.orientation)
    }
    case _                    => {
      //logger.debug("Validate block:")
      valiadteBrick(matrix, block.bricks.head, block.orientation) && valiadteBrick(matrix, block.bricks.tail.head, block.orientation)
    }
  }

*/
  /*  def findPossiblePositions(matrix: Array[Array[Char]]): Set[Block] = {
      //logger.debug(s"Possible solutions")
      var result = allPositions.filter(this.validatePosition(matrix,_))
      //logger.debug(s"         Possible positions for block: ${this} => ${result.mkString(" *** ")}")
      result
    }*/

  /*  private def allPositions(): Set[Block] = {
      var res: Set[Block] = Set()
      for (direction <- Direction.values) res = res + new Block(calcOrientation(direction), calcBricksList(direction))
      res
    }*/
}
