package uk.org.bloxorz

import org.scalatest.FunSuite
import uk.org.bloxorz.game.map.blocks.{Block, Orientation, Point}

class BlockTest extends FunSuite{

  test("Test 1 - Single block - self equality") {
    val p1 = new Point(0,0)
    val or = Orientation.Vertical
    val b1 = new Block(or, p1 :: Nil)
    assert(b1 == b1)
  }

  test("Test 2 - Single block - equality") {
    val p1 = new Point(0,0)
    val p11 = new Point(0,0)
    val b1 = new Block(Orientation.Vertical, p1 :: Nil)
    val b2 = new Block(Orientation.Vertical, p11 :: Nil)
    assert(b1 == b2)
  }

  test("Test 3 - Two blocks - single brick, different orientation") {
    val p1 = new Point(0,0)
    val b1 = new Block(Orientation.Vertical, p1 :: Nil)
    val b2 = new Block(Orientation.EastWest, p1 :: Nil)
    assertResult(false)(b1 == b2)
  }

  test("Test 4 - Two blocks - single brick, different orientation") {
    val p1 = new Point(0,0)
    val p11 = new Point(0,0)
    val b1 = new Block(Orientation.Vertical, p1 :: Nil)
    val b2 = new Block(Orientation.EastWest, p11 :: Nil)
    assertResult(false)(b1 == b2)
  }

  test("Test 4 - two brick, same orientation self") {
    val p1 = new Point(0,0)
    val p11 = new Point(0,0)
    val b1 = new Block(Orientation.Vertical, p1 :: p1 :: Nil)
    val b2 = new Block(Orientation.Vertical, p1 :: p1 :: Nil)
    assertResult(true)(b1 == b2)
  }

  test("Test 5 - two brick, same orientation") {
    val p1 = new Point(0,0)
    val p11 = new Point(0,0)
    val b1 = new Block(Orientation.Vertical, p1 :: p1 :: Nil)
    val b2 = new Block(Orientation.Vertical, p11 :: p11 :: Nil)
    assertResult(true)(b1 == b2)
  }

  test("Test 6 - two brick, same orientation ") {
    val p1 = new Point(0,0)
    val p2 = new Point(0,1)
    val b1 = new Block(Orientation.Vertical, p2 :: p1 :: Nil)
    val b2 = new Block(Orientation.Vertical, p1 :: p2 :: Nil)
    assertResult(true)(b1 == b2)
  }

  test("Test 7 - two brick, same orientation") {
    val p1 = new Point(0,0)
    val p2 = new Point(0,1)
    val b1 = new Block(Orientation.Vertical, p1 :: p1 :: Nil)
    val b2 = new Block(Orientation.Vertical, p2 :: p1 :: Nil)
    assertResult(false)(b1 == b2)
  }

  test("Test 8 - two brick, totally different") {
    val b1 = new Block(Orientation.Vertical, new Point(5,3) :: new Point(5,7) :: Nil)
    val b2 = new Block(Orientation.NorthSouth, new Point(2,4) :: new Point(234,3) :: Nil)
    assertResult(false)(b1 == b2)
  }
}
