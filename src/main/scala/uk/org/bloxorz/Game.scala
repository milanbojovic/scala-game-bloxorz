package uk.org.bloxorz

object Game {
  def main(args: Array[String]): Unit = {
    println("Program started:")

/*    clearScreen()
    Thread.sleep(1000)

    displayMenu()

    Thread.sleep(2000)*/

    uk.org.bloxorz.io.ExternalWorld.loadMap("input_map.txt")


    println("Program finished")
  }
}
