package uk.org.bloxorz.console

object Util {

  def displayMenu(): Unit = {
    var m = Map(
      1 -> "New game",
      2 -> "Load map from file",
      3 -> "Move single",
      4 -> "Move sequence"
    )
    for(el <- m) println(el._1 + ". " + el._2)
  }

  def cls() {
    print("\u001b[2J")
  }
}
