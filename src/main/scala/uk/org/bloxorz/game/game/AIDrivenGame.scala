package uk.org.bloxorz.game.game

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.game.map.blocks.{Block, Orientation}
import uk.org.bloxorz.io.FileSystem

class AIDrivenGame(fileName: String) extends Game(fileName) with LazyLogging {

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(fileName))
  }

  override def play(): Int = {
     def findSol(curr: Block, prev: Block, posibblePos: Set[Block], pathList: List[Block]): List[Block] = {
       if(board.valiadteGameStatus(curr) || posibblePos.isEmpty) pathList
       else {
         var possFiltered = board.findPossiblePositions(curr) - prev ++ posibblePos.tail
         logger.debug(s"         Possible positions for block: ${curr} => ${possFiltered.mkString(" *** ")}")
         findSol(possFiltered.toList.head, curr, possFiltered, curr :: pathList)
       }
     }
    if (findSol(board.block, new Block(Orientation.Vertical, Nil), board.findPossiblePositions(board.block), Nil).nonEmpty) 1
    else 0
  }
}
