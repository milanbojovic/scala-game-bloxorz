package uk.org.bloxorz

import org.scalatest.FunSuite
import uk.org.bloxorz.game.map.blocks.Point

class PointTest extends FunSuite{

  test("Point coordinates can be set in constructor") {
    val ind = new Point(1,2)
    assert(ind.i == 1 && ind.j == 2)
  }

  test("Equals method testing p12 == p24") {
    val p12 = new Point(1,2)
    val p24 = new Point(2,4)

    assertResult(false)(p12 == p24)
  }

  test("Equals method testing p45_66 != p66_45") {
    val p1 = new Point(45,66)
    val p2 = new Point(66,45)
    assertResult(false)(p1 == p2)
  }

  test("Equals method testing p32 == p24") {
    val p32 = new Point(3,2)
    val p24 = new Point(2,4)
    assertResult(false)(p32 == p24)
  }

  test("Equals method testing p00 == p00") {
    val p00 = new Point(0,0)
    assertResult(true)(p00 == p00)
  }

  test("Equals method testing p00 != p24") {
    val p00 = new Point(0,0)
    val p24 = new Point(2,4)
    assertResult(true)(p00 != p24)
  }
}
