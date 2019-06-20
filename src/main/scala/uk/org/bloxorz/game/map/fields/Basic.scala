package uk.org.bloxorz.game.map.fields

import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz._

class Basic(point: Point) extends Field(point) {

  override def toString: String = PanelBasic.toString
}
