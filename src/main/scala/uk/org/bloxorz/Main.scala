package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.Game

object Main extends LazyLogging {

  def main(args: Array[String]): Unit = {

    //console.Util.displayMenu()
    logger.info("Main program started")

    var game = new Game(uk.org.bloxorz.io.ExternalWorld.loadMap("src/main/resources/input_map.txt"))

    game.playInteractive()

  }
}

