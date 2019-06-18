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

  //val start: Point = Game.findPointCoordinatesOf(matrix, Symbols.PanelStart)
  //val finish: Point = Game.findPointCoordinatesOf(matrix, Symbols.PanelFinish)
  //var block = new Block(Orientation.Vertical, start :: Nil)
  //val emptyBlock = new Block(Orientation.Vertical, Nil)
  var menu = new Menu

  val WaitTimeout: Int = 2 * 1000

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

  def isFinalPosition(matrix: Array[Array[Char]], block: Block): Boolean = {
    (new Block(Orientation.Vertical, GameOld.findPointCoordinatesOf(matrix, Symbols.PanelFinish) :: Nil)) == block
  }

  def checkGameStatus(block: Block, validPosition: Boolean): Boolean = { //!!!!!!!!!!!!!!!!!!
    if (block.orientation == Orientation.Vertical && validPosition) {
      logger.debug(s"GameStatus: ${block.bricks.head.i == finish.i && block.bricks.head.j == finish.j}.")
      block.bricks.head == finish
    } else false
  }

  private def setValueAtIndex(matrix: Array[Array[Char]], ind: Point, value: Char): Unit = {
    matrix(ind.i)(ind.j) = value
  }

  def playInteractive(terminal: Terminal): Unit = {
    /*val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()*/

    val reader = terminal.reader()
    var input = -1
    var gameRunning = true
    var gameStatus = false

    logger.debug("Game started in interactive mode.")
    println(this)
    Thread.sleep(1500)

    try {
      while (gameRunning && input != 81 && input != 113) {
        input = reader.read()
        input match {
          case 68 =>
            gameRunning = this.moveBlock(Direction.LEFT)
            gameStatus = checkGameStatus(this.block, gameRunning)
          case 67 =>
            gameRunning = this.moveBlock(Direction.RIGHT)
            gameStatus = checkGameStatus(this.block, gameRunning)
          case 65 =>
            gameRunning = this.moveBlock(Direction.UP)
            gameStatus = checkGameStatus(this.block, gameRunning)
          case 66 =>
            gameRunning = this.moveBlock(Direction.DOWN)
            gameStatus = checkGameStatus(this.block, gameRunning)
          case unknown =>
            logger.debug(s"you pressed a unsupported key $unknown")
        }
      }
      if(gameStatus) println("You won.") else println("You lost.")
      logger.debug(s"Interactive game finished. Game exit status: ${gameStatus}")
    } finally {
      try {
  /*      reader.close();
        terminal.close();*/
      } catch {
        case e: IOException  => logger.debug("ERROR\n"  + e.toString)
      }
    }
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
