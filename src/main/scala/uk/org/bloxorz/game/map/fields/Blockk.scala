package uk.org.bloxorz.game.map.fields

import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz._

class Blockk(point: Point) extends Field(point) {
  override def toString: String = Block.toString
}
