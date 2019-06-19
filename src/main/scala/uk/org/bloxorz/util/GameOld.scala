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
}
*/
