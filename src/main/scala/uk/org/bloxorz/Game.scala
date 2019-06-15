package uk.org.bloxorz

import com.typesafe.scalalogging.LazyLogging

object Game extends LazyLogging{

  def cls() {
    print("\u001b[2J")
  }


  def main(args: Array[String]): Unit = {

    logger.debug("Bloxorz game started.")

    Thread.sleep(1000)

    console.Util.displayMenu()

    Thread.sleep(2000)

    var map = uk.org.bloxorz.io.ExternalWorld.loadMap("src/main/resources/input_map.txt")

    console.Util.printMap(map)

    Thread.sleep(1000)

    cls()

    Thread.sleep(1000)

    logger.debug("Bloxorz game finished.")
  }
}
