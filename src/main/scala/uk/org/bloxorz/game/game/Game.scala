package uk.org.bloxorz.game.game

import uk.org.bloxorz.game.map.Board

abstract class Game(fileName: String) {

  def initializeBoard: Board

  var board: Board = initializeBoard

  def play(): Boolean;

}
