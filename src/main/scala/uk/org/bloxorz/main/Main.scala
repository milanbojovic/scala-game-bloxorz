package uk.org.bloxorz.main

import java.io.IOException

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz._
import uk.org.bloxorz.console.Menu
import uk.org.bloxorz.game.game.{AIDrivenGame, FileDrivenGame, HumanDrivenGame}

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("Program Bloxorz started")

    val menu = new Menu

    val humenDriven = new HumanDrivenGame("src/main/resources/input_map.txt")
    val fileDriven  = new FileDrivenGame("src/main/resources/input_map.txt", "src/main/resources/input_moves_sequence.txt")
    val aiDriven = new AIDrivenGame("src/main/resources/input_map.txt", "src/main/resources/output_moves_sequence.txt")

    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()
    val reader = terminal.reader()
    var input = -1
    var run = true

    try {
      while (run) {
        cls()
        println(menu)
        if (reader != null) {
          input = reader.read()
          input match {
            case 119 | 87 =>
              menu.changeSelection(Direction.UP)
            case 115 | 83 =>
              menu.changeSelection(Direction.DOWN)
            case 13 | 101 => menu.menuSelection match {
              case 2 => humenDriven.play()
              case 3 => fileDriven.play()
              case 4 => aiDriven.play()
              case 6 => run = false
              case _ => logger.debug("Unknown menu selection item !!!")
            }
            case unknown => logger.debug(s"you pressed a unsupported key $unknown")
          }
        }
      }
      logger.info("Program Bloxorz finished")
    } finally {
      try {
        reader.close()
        terminal.close()
      } catch {
        case e: IOException  => logger.debug("ERROOOOOORRRRR" + e.toString)
      }
    }

  }
}
