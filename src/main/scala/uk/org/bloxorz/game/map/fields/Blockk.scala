package uk.org.bloxorz.game.map.fields

import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz.util.Symbols

class Blockk(point: Point) extends Field(point) {
  override def toString: String = Symbols.Block.toString
}
