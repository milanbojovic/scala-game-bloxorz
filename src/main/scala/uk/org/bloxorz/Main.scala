package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz.game.{Direction, Game}

import scala.io.Source

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {
    //console.Util.displayMenu()
    logger.info("Program GameBloxorz started")

    var game = new Game(uk.org.bloxorz.io.FileSystem.loadMap("src/main/resources/input_map.txt"))

    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var run = true

    while (input != 81 && input != 113 && run) {
      var charArr: Array[Char] = Array[Char]()
      game.menu.cls()
      println(game.menu)
      input = reader.read()
      input match {
        case 13  => game.menu.menuSelection match {
          case 2 => game.playInteractive()
          case 3 => game.playAutomatic("src/main/resources/moves_sequence.txt")
        }
        case 119 | 87 =>
          game.menu.changeSelection(Direction.UP)
        case 115 | 83 =>
          game.menu.changeSelection(Direction.DOWN)
        case 81 | 113 =>
          logger.debug(s"Quitting")
        case unknown => {
          logger.debug(s"you pressed a unsupported key $unknown")
          Thread.sleep(2000)
        }
      }
    }
  logger.info("Program finished")

  }
}

