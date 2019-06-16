package uk.org.bloxorz

import java.io.IOException

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz.game.{Direction, Game}

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("Program GameBloxorz started")

    var game = new Game(uk.org.bloxorz.io.FileSystem.loadMap("src/main/resources/input_map.txt"))

    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var run = true

    try {
      while (run) {
        var charArr: Array[Char] = Array[Char]()
        game.menu.cls()
        println(game.menu)
        if (reader != null) {
          input = reader.read()
          input match {
            case 13 | 101 => game.menu.menuSelection match {
              case 2 => game.playInteractive(terminal)
              case 3 => game.playAutomatic("src/main/resources/moves_sequence.txt")
              case 6 => run = false;
              case _ => logger.debug("Unknowg menu selection item !!!")
            }
            case 119 | 87 =>
              game.menu.changeSelection(Direction.UP)
            case 115 | 83 =>
              game.menu.changeSelection(Direction.DOWN)
            case unknown => {
              logger.debug(s"you pressed a unsupported key $unknown")
            }
          }
        }
      }
      logger.info("Program finished")
    } finally {
      try {
        reader.close();
        terminal.close();
      } catch {
        case e: IOException  => logger.debug("ERROOOOOORRRRR" + e.toString)
      }
    }
  }
}

