package uk.org.bloxorz.game

import java.io.IOException

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.{Terminal, TerminalBuilder}
import uk.org.bloxorz.console.Menu
import uk.org.bloxorz.io.FileSystem

class Game(var matrix: Array[Array[Char]]) extends LazyLogging {

  val start: Index = findIndexOf(matrix, Symbols.PanelStart)
  val finish: Index = findIndexOf(matrix, Symbols.PanelFinish)
  var block = new Block(Orientation.Vertical, start :: Nil)
  var menu = new Menu

  val WaitTimeout: Int = 2 * 1000

  def setBlock(matrix: Array[Array[Char]], blk: Block): Unit = {
    block = blk
  }

  def moveBlock(dir: Direction.Value): Boolean = {
    val newBlock: Block = new Block(block.changeOrientation(dir), block.calcBricksList(dir))
    logger.debug("Moving block: " + block + " ==> " + newBlock)
    setBlock(matrix, newBlock)
    menu.cls()
    println(this)
    validatePosition(block)
  }

  def checkGameStatus(block: Block, validPosition: Boolean): Boolean = {
    if (block.orientation == Orientation.Vertical && validPosition) {
      logger.debug(s"GameStatus: ${block.bricks.head.i == finish.i && block.bricks.head.j == finish.j}.")
      block.bricks.head == finish
    } else false
  }

  def validatePosition(block: Block): Boolean = block.orientation match {
    case Orientation.Vertical => {
      logger.debug("Validate block:")
      valiadteBrick(block.bricks.head, block.orientation)
    }
    case _                    => {
      logger.debug("Validate block:")
      valiadteBrick(block.bricks.head, block.orientation) && valiadteBrick(block.bricks.tail.head, block.orientation)
    }
  }

  def valiadteBrick(brick: Index, orientation: Orientation.Value): Boolean = orientation match {
    case Orientation.Vertical =>  {
      logger.debug(s"     ValidateBrick: ${brick}, ${orientation}, matrix${brick}=${matrix(brick.i)(brick.j)} - status => ${matrix(brick.i)(brick.j) != Symbols.PanelEmpty && matrix(brick.i)(brick.j) != Symbols.PanelSpecial}")
      matrix(brick.i)(brick.j) != Symbols.PanelEmpty && matrix(brick.i)(brick.j) != Symbols.PanelSpecial;
    }
    case _                    =>  {
      logger.debug(s"     ValidateBrick: ${brick}, ${orientation}, matrix${brick}=${matrix(brick.i)(brick.j)} - status => ${matrix(brick.i)(brick.j) != Symbols.PanelEmpty}")
      matrix(brick.i)(brick.j) != Symbols.PanelEmpty
    }
  }

  private def setValueAtIndex(matrix: Array[Array[Char]], ind: Index, value: Char): Unit = {
    matrix(ind.i)(ind.j) = value
  }

  def findIndexOf(matrix: Array[Array[Char]], c: Char): Index = {
    def findHelper(i: Int): Index = {
      if (i == matrix.length) new Index(-1, -1)
      else if (matrix(i).indexOf(c.toUpper) >= 0) new Index(i, matrix(i).indexOf(c.toUpper))
      else findHelper(i + 1)
    }
    findHelper(0)
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
