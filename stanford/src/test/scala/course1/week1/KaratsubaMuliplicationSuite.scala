package course1.week1


import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import KaratsubaMuliplication._

@RunWith(classOf[JUnitRunner])
class KaratsubaMuliplicationSuite extends FunSuite {
  
  test("test 1 * 2") {
    assert(1 * 2 === cal(1, 2))
  }
  
  test("test 6789 * 2") {
    assert(6789 * 2 === cal(6789, 2))
  }
  
  test("test 6789 * 1234") {
    assert(6789 * 1234 === cal(6789, 1234))
  }
  
  test("test 178962343243 * 2983274987653") {
    assert(178962343243L * 2983274987653L === cal(178962343243L, 2983274987653L))
  }
  
}
