package uk.org.bloxorz.main

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.game.HumanDrivenGame

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {
    logger.info("Program GameBloxorz started")

    var game = new HumanDrivenGame("src/main/resources/input_map.txt")
    game.play()

/*    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var run = true*/

    //game.findSolution()

   /* try {
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
              case 4 => logger.debug(game.findSolution().mkString("=========>") + "*********************" )
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
    }*/
  }
}
