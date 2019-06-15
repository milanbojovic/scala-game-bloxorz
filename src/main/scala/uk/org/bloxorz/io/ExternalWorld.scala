package uk.org.bloxorz.io

import java.io.{FileNotFoundException, IOException}

import com.typesafe.scalalogging.LazyLogging

import scala.io.{BufferedSource, Source}

object ExternalWorld extends LazyLogging {

  def loadMap(filename: String): Array[Array[Char]] = {

    try {
      Source.fromFile(filename).getLines().toArray[String].map(_.toUpperCase().toArray)
    } catch {
      case e: FileNotFoundException => logger.error("Couldn't find " + filename + " file."); Array[Array[Char]]();
      case e: IOException => logger.error("Got an IOException while loadingMap from file! [" + filename + "]"); Array[Array[Char]]()
    }
  }
}