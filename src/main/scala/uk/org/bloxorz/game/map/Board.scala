package uk.org.bloxorz.game.map

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.blocks.{Block, Point}
import uk.org.bloxorz.game.map.fields.{Basic, End, Field, Start}
import uk.org.bloxorz.util.Util

class Board(rawMatrix: Vector[Vector[Char]]) extends LazyLogging {
  logger.debug("Checking constraints and initializing board")

  // Requirements
  require(rawMatrix.nonEmpty && rawMatrix(0).nonEmpty)
  require(findStartFields.size == 1, "Map must have one \"START\" field !!!")
  require(findEndFields.size == 1, "Map must have one \"END\" field !!!")
  require(findBasicFields.nonEmpty, "Map must have at least one \"BASIC\" field")

  // Matrix dimensions
  val sizeM: Int = rawMatrix.size
  val sizeN: Int = rawMatrix(0).size

  // Matrix
  val fields: Vector[Vector[Field]] = initializeFieldMatrix(rawMatrix)
  logger.debug("Loading map: ")
  logger.debug(fields.toString())

  logger.debug("Loading completed - matrix is valid")

  // Matrix start, end fields
  val startField  : Start   = findStartFields.head.asInstanceOf[Start]
  val endField    : End     = findEndFields.head.asInstanceOf[End]




  // Method definitions
  def initializeFieldMatrix(vectorMatrix: Vector[Vector[Char]]) : Vector[Vector[Field]] = {
    logger.debug("Initializing fields matrix!")
    Vector.tabulate(sizeM,sizeN){ (i,j) => Util.charToField(vectorMatrix(i)(j), new Point(i,j))}
  }

  def validatePosition(p: Point): Boolean = {
    p.i >= 0 && p.i < sizeM && p.j >= 0 && p.j < sizeN
  }

  def validatePosition(b: Block): Unit = {
    //p.i >= 0 && p.i < sizeM && p.j >= 0 && p.j < sizeN
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

  override def toString: String = {
    fields.map(_.mkString).mkString("\n")
  }
}
