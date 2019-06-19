package uk.org.bloxorz.game.game

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.Board
import uk.org.bloxorz.game.map.blocks.Direction
import uk.org.bloxorz.io.FileSystem
import uk.org.bloxorz.util.Util

class FileDrivenGame(mapFile: String, movesFile: String) extends Game(mapFile) with LazyLogging {

  val WaitTimeout: Int = 1 * 1000

  override def initializeBoard: Board = {
    logger.debug("Initializing game")
    new Board(FileSystem.loadMap(mapFile))
  }

  override def play(): Int = {
    logger.debug(this + " started:")
    println(this + " started")

    println(this)
    Thread.sleep(WaitTimeout)

    var gameControl: (Boolean, Boolean) = (true, true)
    for(move <- FileSystem.loadMoves(movesFile)) {
      Util.cls()
      println(board)
      if (gameControl._1) {
        gameControl = board.moveBlock(keyDecoder(move.toInt))
        Thread.sleep(WaitTimeout)
      }
    }
    logger.debug(s"$this  finished - EXIT STATUS: ${gameControl._2}")
    if(gameControl._2) {println("You won.");  0} else println("You lost."); 1
  }

  def keyDecoder(key: Int) : Direction.Value = key match {
    case 67 | 100 => Direction.RIGHT
    case 65 | 97 => Direction.LEFT
    case 68 | 119 | 87 =>Direction.UP
    case 66 | 115 | 88 => Direction.DOWN
  }

}
