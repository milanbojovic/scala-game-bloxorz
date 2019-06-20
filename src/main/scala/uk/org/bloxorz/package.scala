package uk.org

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz.game.map.fields._

package object bloxorz extends LazyLogging{

  val PanelBasic   = 'O'
  val PanelSpecial = '.'
  val PanelStart   = 'S'
  val PanelFinish  = 'T'
  val PanelEmpty   = '-'
  val Block        = '#'

  object Direction extends Enumeration {
    val UP, DOWN, LEFT, RIGHT = Value
  }

  object Orientation extends Enumeration {
    val EastWest, NorthSouth, Vertical = Value
  }

  def charToField(c: Char, p: Point): Field = c.toUpper match {
    case PanelBasic   => new Basic(p)
    case PanelSpecial => new Special(p)
    case PanelStart   => new Start(p)
    case PanelFinish  => new End(p)
    case PanelEmpty   => new Empty(p)
    case Block        => new Blockk(p)
    case _ => {
      val errorMsg = s"Error: Invalid input file, bad character detected while parsing input file. Point $p, value: '$c'"
      print(errorMsg)
      logger.debug(errorMsg)
      System.exit(1)
      new Basic(new Point(1,1)) //TODO REMMOVE USE OPTIONS
    }
  }

  def cls(): Unit = {
    print("\u001b[2J")

  }










}
