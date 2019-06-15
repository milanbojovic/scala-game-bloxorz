package uk.org.bloxorz.game

class GameMap(var matrix: Array[Array[Char]]) {

  setValueAtIndex(findIndexOf(Symbols.PanelStart), Symbols.PanelBasic)

  def start: Index = findIndexOf(Symbols.PanelStart)
  def finish: Index = findIndexOf(Symbols.PanelFinish)

  def setValueAtIndex(ind: Index, value: Char): Unit = {
    matrix(ind.i)(ind.j) = value
  }

  def findIndexOf(c: Char): Index = {
    def matrixSize = matrix.size
    def findHelper(i: Int): Index = {
      if (i == matrixSize) new Index(-1, -1)
      else if (matrix(i).indexOf(c.toUpper) >= 0) new Index(i, matrix(i).indexOf(c.toUpper))
      else findHelper(i+1)
    }
    findHelper(0)
  }

  override def toString: String = {
    matrix.map(_.mkString).mkString("\n")
  }
}
