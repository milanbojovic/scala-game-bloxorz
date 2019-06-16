package uk.org.bloxorz.io

import java.io.{FileNotFoundException, IOException}

import com.typesafe.scalalogging.LazyLogging

import scala.io.Source

object FileSystem extends LazyLogging {

  def loadMap(filename: String): Array[Array[Char]] = {

    try {
      Source.fromFile(filename).getLines().toArray[String].map(_.toUpperCase().toArray)
    } catch {
      case e: FileNotFoundException => logger.error(s"Couldn't find file [${filename}]."); Array[Array[Char]]();
      case e: IOException => logger.error(s"Got an IOException while loadingMap from file! [${filename}]"); Array[Array[Char]]()
    }
  }

  def encodeMove(move: String): String = move.trim match {
    case "u" | "U" | "\"u\"" | "\"U\"" =>  "65"
    case "d" | "D" | "\"d\"" | "\"D\"" =>  "66"
    case "r" | "R" | "\"r\"" | "\"R\"" =>  "67"
    case "l" | "L" | "\"l\"" | "\"L\"" =>  "68"
    case _ => throw new IOException(s"Invalid value detected while reading file. [${move.trim}]")

  }
  def loadMoves(filename: String): List[String] = {

    try {
      Source.fromFile(filename).getLines().toList.iterator.map(encodeMove(_)).toList
    } catch {
      case e: FileNotFoundException => logger.error(s"Couldn't find file [${filename}]."); List[String]();
      case e: IOException => logger.error(s"Got an IOException while loadingMap from file! [${filename}]"); List[String]()
    }
  }
}