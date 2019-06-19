package uk.org.bloxorz.util

import java.io.IOException

import uk.org.bloxorz.game.map.blocks.Point
import uk.org.bloxorz.game.map.fields._

object Util {
    def charToField(c: Char, p: Point): Field = c.toUpper match {
      case Symbols.PanelBasic   => new Basic(p)
      case Symbols.PanelSpecial => new Special(p)
      case Symbols.PanelStart   => new Start(p)
      case Symbols.PanelFinish  => new End(p)
      case Symbols.PanelEmpty   => new Empty(p)
      case Symbols.Block        => new Blockk(p)
      case _ => throw new IOException(s"Invalid value detected while reading file. Point $p, value: $p")
    }

    def cls(): Unit = {
      print("\u001b[2J")
    }
}