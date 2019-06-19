package uk.org.bloxorz.game.game

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.game.map.blocks.{Block, Direction, Orientation}
import uk.org.bloxorz.io.FileSystem

class AIDrivenGame(fileName: String, outputMoves: String) extends Game(fileName) with LazyLogging {

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(fileName))
  }

  override def play(): Int = {

    val pathHist: List[Block] = findSolution(board.block, Nil)
    val res: List[Direction.Value] = translateToMoves(pathHist).reverse
    println(res.mkString(" => "))
    logger.debug(res.mkString(" => "))
    FileSystem.storeMoves(outputMoves, res)
    1
  }

  def translateToMoves(movesList: List[Block]): List[Direction.Value] = {
    var resList: List[Direction.Value] = Nil
    val list = (new Block(Orientation.Vertical, board.endField.point :: Nil) :: movesList).reverse
    var currBlk = list.head


    for(move <- list.tail) {
      val found = currBlk.allPositions().zipWithIndex.filter(_._1 == move).head
      currBlk = found._1
      resList = decodeDirection(found._2) :: resList
    }
    resList
  }


  private def decodeDirection(num: Int): Direction.Value = num match {
    case 3 => Direction.RIGHT
    case 2 => Direction.LEFT
    case 1 => Direction.DOWN
    case 0 => Direction.UP
  }

  def findSolution(curr: Block, pathHist: List[Block]): List[Block] = {
    val fpp = excludeVisited(board.findPossiblePositions(curr), pathHist)
    val gs = board.valiadteGameStatus(curr)
    if(gs || fpp.isEmpty) {
      if (gs) { logger.info(s"Success Game finished: $pathHist")}
      pathHist
    } else {
      logger.debug(s"         Possible positions for block: $curr => ${fpp.mkString(" *** ")}")
      findSolution(fpp.toList.head, curr :: pathHist)
    }
  }

  def excludeVisited(set: Set[Block], list: List[Block]): Set[Block] = {
    if(list.isEmpty) set else set.-(list.head)
  }
}
