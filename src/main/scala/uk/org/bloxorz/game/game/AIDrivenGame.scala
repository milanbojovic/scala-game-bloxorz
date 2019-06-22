package uk.org.bloxorz.game.game

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.game.map.blocks.Block
import uk.org.bloxorz.io.FileSystem
import uk.org.bloxorz._

class AIDrivenGame(fileName: String, outputMoves: String) extends Game(fileName) with LazyLogging {

  var solutions: List[Block] = Nil

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(fileName))
  }

  override def play(): Int = {
    board.initializeBlockPosition()
    solutions = Nil
    findSolutions(board.block, Nil)
    val res: List[Direction.Value] = translateToMoves(solutions.reverse).reverse
    println(res.mkString("GAME RESULT: ", " => ", ""))
    logger.debug(res.mkString("GAME RESULT: ", " => ", ""))
    FileSystem.storeMoves(outputMoves, res)
    1
  }

  def translateToMoves(movesList: List[Block]): List[Direction.Value] = {
    var resList: List[Direction.Value] = Nil

    if(movesList.nonEmpty) {
      var currBlk = movesList.head
      if (movesList.tail.nonEmpty) {
        for(move <- movesList.tail) {
          val svePozicije = currBlk.allPositions()
          val svePozicijeZipped = svePozicije.zipWithIndex
            val nadjenaPozicija: Set[(Block, Int)] = svePozicijeZipped.filter(_._1 == move)
          val playedMove = nadjenaPozicija.head
          currBlk = playedMove._1
          resList = decodeDirection(playedMove._2) :: resList
        }
      }
    }
    resList
  }

  private def decodeDirection(num: Int): Direction.Value = num match {
    case 3 => Direction.RIGHT
    case 2 => Direction.LEFT
    case 1 => Direction.DOWN
    case 0 => Direction.UP
  }

  def findSolutions(curr: Block, pathHist: List[Block]): Unit= {
    val gs = board.valiadteGameStatus(curr)
    val fpp = excludeVisited((board.findPossiblePositions(curr)), pathHist)

    if(gs || fpp.isEmpty) {
      if (gs) {
        if (solutions.isEmpty) {
          solutions = (curr :: pathHist)
        } else if (solutions.size > pathHist.size + 1) {
          solutions = (curr :: pathHist)
        }
      }
    } else {
      logger.debug(s"         Possible positions for block: $curr => ${fpp.mkString(" *** ")}")
      fpp.foreach(findSolutions(_, curr :: pathHist))
    }
  }

  def excludeVisited(possib: Set[Block], visited: List[Block]): Set[Block] = {
    if(visited.isEmpty) possib else possib.filter(!visited.contains(_))
  }
}
