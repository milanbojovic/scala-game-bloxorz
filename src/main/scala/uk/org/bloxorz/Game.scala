package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz.game.{Direction, GameMap}

object Game extends LazyLogging {

  def cls() {
    print("\u001b[2J")
  }

  def play(gameMap: GameMap): Unit = {
    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var run = true

    logger.debug("Bloxorz game started.")
    println(gameMap)
    Thread.sleep(1500)

    while (input != 81 && input != 113 && run) {
      input = reader.read()
      input match {
        case 68 =>
          run = gameMap.moveBlock(Direction.LEFT)
        case 67 =>
          run = gameMap.moveBlock(Direction.RIGHT)
        case 65 =>
          run = gameMap.moveBlock(Direction.UP)
        case 66 =>
          run = gameMap.moveBlock(Direction.DOWN)
        case unknown =>
          println(s"you pressed a unsupported key $unknown")
      }
    }
    terminal.close()
    reader.close()

    logger.debug("Bloxorz game finished.")
  }

  def main(args: Array[String]): Unit = {
    //console.Util.displayMenu()

    var gameMap = new GameMap(uk.org.bloxorz.io.ExternalWorld.loadMap("src/main/resources/input_map.txt"))

    play(gameMap)

  }
}

