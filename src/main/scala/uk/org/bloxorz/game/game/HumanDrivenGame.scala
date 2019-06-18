package uk.org.bloxorz.game.game

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.io.FileSystem

class HumanDrivenGame(fileName: String) extends Game(fileName) with LazyLogging {

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(fileName))
  }

  override def play(): Boolean = ???
}
