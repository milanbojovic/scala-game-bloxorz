package uk.org.bloxorz.game.map

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.blocks.{Block, Direction, Orientation, Point}
import uk.org.bloxorz.game.map.fields._
import uk.org.bloxorz.util.{Symbols, Util}

class Board(rawMatrix: Vector[Vector[Char]]) extends LazyLogging {
  logger.debug("Checking constraints and initializing board")

  // Requirements 1
  require(rawMatrix.nonEmpty && rawMatrix(0).nonEmpty)

  // Matrix dimensions
  val sizeM: Int = rawMatrix.size
  val sizeN: Int = rawMatrix(0).size

  // Matrix
  var fields: Vector[Vector[Field]] = initializeFieldMatrix(rawMatrix)
  logger.debug("Loading map: ")
  logger.debug(fields.toString())
  logger.debug("Loading completed - matrix is valid")

  // Requirements 2
  require(findStartFields.size == 1, "Map must have one \"START\" field !!!")
  require(findEndFields.size == 1, "Map must have one \"END\" field !!!")
  require(findBasicFields.nonEmpty, "Map must have at least one \"BASIC\" field")



  // Matrix start, end fields
  val startField  : Start   = findStartFields.head.asInstanceOf[Start]
  val endField    : End     = findEndFields.head.asInstanceOf[End]

  // Block
  var block: Block = new Block(Orientation.Vertical, List(startField.point))

  // Method definitions
  def moveBlock(dir: Direction.Value): (Boolean,Boolean) = {
    val newBlock = block.moveBlock(dir)
    if(valiadteBlock(newBlock)) {
      logger.debug("Moving block: " + block + " ==> " + newBlock)
      block = newBlock
      (!valiadteGameStatus(newBlock), valiadteGameStatus(newBlock))
    } else (false, false)
  }

  def initializeFieldMatrix(vectorMatrix: Vector[Vector[Char]]) : Vector[Vector[Field]] = {
    logger.debug("Initializing fields matrix!")
    Vector.tabulate(sizeM,sizeN){ (i,j) => Util.charToField(vectorMatrix(i)(j), new Point(i,j))}
  }

  def valiadteGameStatus(block: Block): Boolean = {
    logger.debug(s"Validate game status: Block current $block, end field coordinates: ${endField.point}")
    block.orientation == Orientation.Vertical && block.bricks.head == endField.point
  }

  private def valiadteBlock(b: Block): Boolean = {
    val innerMatch = b.orientation match {
      case Orientation.Vertical => !getField(b.bricks.head).isInstanceOf[Empty] && !getField(b.bricks.head).isInstanceOf[Special]
      case _                    => !b.bricks.exists(getField(_).isInstanceOf[Empty])
    }
    logger.debug(s"     Validating b: $b,")
    if (b.bricks.forall(_.isValid((sizeM,sizeN)))) innerMatch else false
  }

  def getField(p: Point): Field = {
    fields(p.i)(p.j)
  }

  def findStartFields: Vector[Field] = {
    fields.flatten.filter(_.isInstanceOf[Start])
  }

  def findEndFields: Vector[Field] = {
    fields.flatten.filter(_.isInstanceOf[End])
  }

  def findBasicFields: Vector[Field] = {
    fields.flatten.filter(_.isInstanceOf[Basic])
  }

  def findEmptyFields: Vector[Field] = {
    fields.flatten.filter(_.isInstanceOf[Empty])
  }

/*  def filterFieldsByType(zz: Class[Any]): Vector[Field] = zz.getCanonicalName match {
    case "Start" => fields.flatten.filter(_.isInstanceOf[Special])
    case _ => fields.flatten.empty
  }*/

  override def toString: String = {
    fields.map(_.map(addBlockField(_)).mkString).mkString("\n")
  }

  def addBlockField(f:Field): Field = {
    if (block.bricks.contains(f.point)) new Blockk(f.point).asInstanceOf[Field]
    else f
  }
}