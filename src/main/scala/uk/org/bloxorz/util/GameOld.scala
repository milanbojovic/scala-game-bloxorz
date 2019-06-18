/*
package uk.org.bloxorz.game

import java.io.IOException

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.{Terminal, TerminalBuilder}
import uk.org.bloxorz.console.Menu
import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz.io.FileSystem

object GameOld {

}
class GameOld(var matrix: Vector[Vector[Char]]) extends LazyLogging {


  var menu = new Menu



  //logger.debug(s"findSolutions for block: ${block}")
  def findSolution(): List[Block] = {
    def findSol(curr: Block, prev: Block, posibblePos: Set[Block], pathList: List[Block]): List[Block] = {
      if(isFinalPosition(matrix, curr) || posibblePos.isEmpty) pathList
      else {
        var possFiltered = curr.findPossiblePositions(matrix) - prev ++ posibblePos.tail
        logger.debug(s"         Possible positions for block: ${curr} => ${possFiltered.mkString(" *** ")}")
        findSol(possFiltered.toList.head, curr, possFiltered, curr :: pathList)
      }
    }
    findSol(block, emptyBlock, block.findPossiblePositions(matrix), Nil)
  }

  def playAutomatic(fileName: String): Unit = {
    logger.debug("Game started in automatic mode.")
    println(this)
    Thread.sleep(WaitTimeout)

    var run = true
    for(move <- FileSystem.loadMoves(fileName)) {
      if (run) {
        run = matchMove(move.toInt)
        Thread.sleep(WaitTimeout)
      }
    }
    logger.debug("Automatic game finished.")
  }

  def matchMove(move: Int): Boolean = {
    move match {
      case 68 =>
        this.moveBlock(Direction.LEFT)
      case 67 =>
        this.moveBlock(Direction.RIGHT)
      case 65 =>
        this.moveBlock(Direction.UP)
      case 66 =>
        this.moveBlock(Direction.DOWN)
      case unknown =>
        println(s"you pressed a unsupported key $unknown")
        false
    }
  }

  override def toString: String = {
    val tmpMatrix = matrix.map(_.clone)
    //Set block on board
    block.bricks.foreach(setValueAtIndex(tmpMatrix, _, Symbols.Block))
    tmpMatrix.map(_.mkString).mkString("\n")
  }
}
*/
