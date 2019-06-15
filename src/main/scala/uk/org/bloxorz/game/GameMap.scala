package uk.org.bloxorz.game

import javax.swing.JSplitPane
import uk.org.bloxorz.Game

class GameMap(var matrix: Array[Array[Char]]) {

  val start: Index = findIndexOf(matrix, Symbols.PanelStart)
  val finish: Index = findIndexOf(matrix, Symbols.PanelFinish)
  var block = new Block(Orientation.Vertical, start :: Nil)

  def setBlock(matrix: Array[Array[Char]], blk: Block): Unit = {
    block = blk
  }

  def moveBlock(dir: Direction.Value): Boolean = {
    setBlock(matrix, new Block(block.changeOrientation(dir), block.calculateChunkIndexList(dir)))
    Game.cls()
    println(this)
    validatePosition(block)
  }

  def validatePosition(block: Block): Boolean = block.orientation match {
    case Orientation.Vertical => {
      valiadteBrick(block.bricks.head, block.orientation)
    }
    case _                    => {
      valiadteBrick(block.bricks.head, block.orientation)
      valiadteBrick(block.bricks.tail.head, block.orientation)
    }

  }

  def valiadteBrick(brick: Index, orientation: Orientation.Value): Boolean = orientation match {
    case Orientation.Vertical => {
        matrix(brick.i)(brick.j) != Symbols.PanelEmpty &&
        matrix(brick.i)(brick.j) != Symbols.PanelSpecial
    }
    case _                    => {
      matrix(brick.i)(brick.j) != Symbols.PanelEmpty
    }
  }

  private def setValueAtIndex(matrix: Array[Array[Char]], ind: Index, value: Char): Unit = {
    matrix(ind.i)(ind.j) = value
  }

  def findIndexOf(matrix: Array[Array[Char]], c: Char): Index = {
    def findHelper(i: Int): Index = {
      if (i == matrix.length) new Index(-1, -1)
      else if (matrix(i).indexOf(c.toUpper) >= 0) new Index(i, matrix(i).indexOf(c.toUpper))
      else findHelper(i + 1)
    }

    findHelper(0)
  }

  override def toString: String = {
    val tmpMatrix = matrix.map(_.clone)
    println("Chunk list: [" + block.bricks.size + "]")
    block.bricks.foreach(setValueAtIndex(tmpMatrix, _, Symbols.Block))
    tmpMatrix.map(_.mkString).mkString("\n")
  }
}
