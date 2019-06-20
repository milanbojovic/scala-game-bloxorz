package uk.org.bloxorz.game.map.fields

import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz._

class End(point: Point) extends Field(point) {

  override def toString: String = PanelFinish.toString
}
