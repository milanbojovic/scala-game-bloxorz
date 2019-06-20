package uk.org.bloxorz.console

import com.typesafe.scalalogging.LazyLogging

import uk.org.bloxorz._

class Menu extends LazyLogging {
    var menuSelection: Int = 1

    val menuItems: Map[Int, String] = Map(
      1 -> "Load map",
      2 -> "New interactive game",
      3 -> "New automatic game",
      4 -> "Find solution sequence",
      6 -> "Exit"
    )

  def changeSelection(direction: Direction.Value): Unit = direction match {
      case Direction.DOWN => if (menuSelection < menuItems.keys.size) {
        logger.debug(s"Change selection event - selection $menuSelection, direction $direction")
        menuSelection = menuSelection + 1
      }
      case Direction.UP => if (menuSelection > 1) {
        logger.debug(s"Change selection event - selection $menuSelection, direction $direction")
        menuSelection = menuSelection - 1
      }
  }

  override def toString: String = {
    menuItems.toSeq.sortBy(_._1).map(k => if(k._1 == menuSelection) "[" +  k + "]" else k).mkString("\n")
  }

  def cls() {
    print("\u001b[2J")
  }
}
