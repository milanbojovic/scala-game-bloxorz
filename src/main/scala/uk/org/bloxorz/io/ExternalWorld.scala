package uk.org.bloxorz.io

import scala.io.Source
import java.io.{FileNotFoundException, IOException}

import com.typesafe.scalalogging.LazyLogging

object ExternalWorld extends LazyLogging {

  def loadMap(filename: String): Unit = {
    try {
      val src = Source.fromFile(filename)
      for (line <- src.getLines) {
        println(line)
      }
      src.close()
    } catch {
      case e: FileNotFoundException => logger.error("Couldn't find " + filename + " file.")
      case e: IOException => logger.error("Got an IOException while loadingMap from file! [" + filename + "]")
    }
  }
}

