package uk.org.bloxorz.io

import scala.io.Source
import java.io.{FileNotFoundException, IOException}

object ExternalWorld {

  def loadMap(filename: String): Unit = {
    try {
      for (line <- Source.fromFile(filename).getLines) {
        println(line)
      }
    } catch {
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Got an IOException!")
    }
  }
}

