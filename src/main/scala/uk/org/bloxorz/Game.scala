package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.GameMap

object Game extends LazyLogging{

  def cls() {
    print("\u001b[2J")
  }

  def main(args: Array[String]): Unit = {
    //console.Util.displayMenu()

    logger.debug("Bloxorz game started.")

    var gameMap = new GameMap(uk.org.bloxorz.io.ExternalWorld.loadMap("src/main/resources/input_map.txt"))
    println(gameMap)

    logger.debug("Bloxorz game finished.")
  }
}
