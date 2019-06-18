package uk.org.bloxorz.game.game

import java.io.IOException

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.game.map.blocks.Direction
import uk.org.bloxorz.io.FileSystem
import uk.org.bloxorz.util.Util

class HumanDrivenGame(fileName: String) extends Game(fileName) with LazyLogging {

  val WaitTimeout: Int = 1 * 1000

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(fileName))
  }

  override def play(): Int = {
    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var gameControl: (Boolean, Boolean) = (true, true)

    logger.debug(this + " started:")
    println(this + " started")
    println("Please use arrow key for navigation: ")
    Thread.sleep(WaitTimeout)

    try {
      while (gameControl._1 && input != 81 && input != 113) {
        Util.cls()
        println(board)
        input = reader.read()
        input match {
          case 65 | 66 | 67 | 68 | 87 | 88 | 97 | 100 | 115 | 119 =>
            gameControl = board.moveBlock(keyDecoder(input))
          case unknown =>
            logger.debug(s"you pressed a unsupported key $unknown")
        }
      }
      logger.debug(s"$this  finished - EXIT STATUS: ${gameControl._2}")
      if(gameControl._2) {println("You won.");  0} else println("You lost."); 1
    } finally {
      try {
        /*reader.close();
              terminal.close();*/
      } catch {
        case e: IOException  => logger.debug("ERROR\n"  + e.toString)
      }
    }
  }

  def keyDecoder(key: Int) : Direction.Value = key match {
    case 67 | 100 => Direction.RIGHT
    case 65 | 97 => Direction.LEFT
    case 68 | 119 | 87 =>Direction.UP
    case 66 | 115 | 88 => Direction.DOWN
  }

  override def toString: String = "Human Driven " + super.toString
}
