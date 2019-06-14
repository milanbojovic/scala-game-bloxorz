package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging

object Game extends LazyLogging{
  def main(args: Array[String]): Unit = {

    logger.debug("Bloxorz game started.")

/*    clearScreen()
    Thread.sleep(1000)

    displayMenu()

    Thread.sleep(2000)*/

    uk.org.bloxorz.io.ExternalWorld.loadMap("src/main/resources/input_map.txt")

    logger.debug("Bloxorz game finished.")
  }
}
