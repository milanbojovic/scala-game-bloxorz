package uk.org.bloxorz.game

import com.typesafe.scalalogging.LazyLogging
import org.jline.terminal.TerminalBuilder
import uk.org.bloxorz.console.Util

class Game(var matrix: Array[Array[Char]]) extends LazyLogging {

  val start: Index = findIndexOf(matrix, Symbols.PanelStart)
  val finish: Index = findIndexOf(matrix, Symbols.PanelFinish)
  var block = new Block(Orientation.Vertical, start :: Nil)

  def setBlock(matrix: Array[Array[Char]], blk: Block): Unit = {
    block = blk
  }

  def moveBlock(dir: Direction.Value): Boolean = {
    val newBlock: Block = new Block(block.changeOrientation(dir), block.calculateChunkIndexList(dir))
    logger.debug("Moving block: " + block + " ==> " + newBlock)
    setBlock(matrix, newBlock)
    Util.cls()
    println(this)
    validatePosition(block)
  }

  def validatePosition(block: Block): Boolean = block.orientation match {
    case Orientation.Vertical => valiadteBrick(block.bricks.head, block.orientation)
    case _                    => valiadteBrick(block.bricks.head, block.orientation); valiadteBrick(block.bricks.tail.head, block.orientation)
  }

  def valiadteBrick(brick: Index, orientation: Orientation.Value): Boolean = orientation match {
    case Orientation.Vertical =>  matrix(brick.i)(brick.j) != Symbols.PanelEmpty && matrix(brick.i)(brick.j) != Symbols.PanelSpecial
    case _                    =>  matrix(brick.i)(brick.j) != Symbols.PanelEmpty
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

  def playInteractive(): Unit = {
    val terminal = TerminalBuilder.builder().jna(true).system(true).build()
    terminal.enterRawMode()

    val reader = terminal.reader()
    var input = -1
    var run = true

    logger.debug("Bloxorz game started.")
    println(this)
    Thread.sleep(1500)

    while (input != 81 && input != 113 && run) {
      input = reader.read()
      input match {
        case 68 =>
          run = this.moveBlock(Direction.LEFT)
        case 67 =>
          run = this.moveBlock(Direction.RIGHT)
        case 65 =>
          run = this.moveBlock(Direction.UP)
        case 66 =>
          run = this.moveBlock(Direction.DOWN)
        case unknown =>
          println(s"you pressed a unsupported key $unknown")
      }
    }
    terminal.close()
    reader.close()

    logger.debug("Bloxorz game finished.")
  }

  override def toString: String = {
    val tmpMatrix = matrix.map(_.clone)
    block.bricks.foreach(setValueAtIndex(tmpMatrix, _, Symbols.Block))
    tmpMatrix.map(_.mkString).mkString("\n")
  }
}
