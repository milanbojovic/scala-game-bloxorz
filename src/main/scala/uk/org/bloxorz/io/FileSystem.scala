package uk.org.bloxorz.io

import java.io.{FileNotFoundException, IOException}

import com.typesafe.scalalogging.LazyLogging
import uk.org.bloxorz.game.map.blocks.Direction

import scala.io.Source

object FileSystem extends LazyLogging {

  def loadMap(filename: String): Vector[Vector[Char]] = {
    try {
      logger.debug(s"Loading map from file + $filename")
      Source.fromFile(filename).getLines().toVector.map(_.trim.toVector)
    } catch {
      case e: FileNotFoundException => logger.error(s"Couldn't find file [$filename]."); Vector[Vector[Char]]();
      case e: IOException => logger.error(s"Got an IOException while loadingMap from file! [$filename]"); Vector[Vector[Char]]()
    }
  }

  def encodeMove(move: String): String = move.trim match {
    case "u" | "U" | "\"u\"" | "\"U\"" =>  "65"
    case "d" | "D" | "\"d\"" | "\"D\"" =>  "66"
    case "r" | "R" | "\"r\"" | "\"R\"" =>  "67"
    case "l" | "L" | "\"l\"" | "\"L\"" =>  "68"
    case _ => throw new IOException(s"Invalid value detected while reading file. [$move.trim]")

  }

  def loadMoves(filename: String): List[String] = {
    try {
      logger.debug(s"sLoading moves from file + $filename")
      Source.fromFile(filename).getLines().toList.iterator.map(encodeMove(_)).toList
    } catch {
      case e: FileNotFoundException => logger.error(s"Couldn't find file [$filename]."); List[String]();
      case e: IOException => logger.error(s"Got an IOException while loadingMap from file! [$filename]"); List[String]()
    }
  }

  def storeMoves(filename: String, moves: List[Direction.Value]) = {
    try {
      logger.debug(s"sStoring moves to file + $filename")
      //Source.fromFile(filename).getLines().toList.iterator.map(encodeMove(_)).toList
      reflect.io.File(filename).writeAll(moves.mkString("\n"))
    } catch {
      case e: FileNotFoundException => logger.error(s"Couldn't find file [$filename]."); List[String]();
      case e: IOException => logger.error(s"Got an IOException while loadingMap from file! [$filename]"); List[String]()
    }
  }
}