package course1.week2

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.util.Sorting

@RunWith(classOf[JUnitRunner])
class CountInversionSuite extends FunSuite{
  test("test1") {
    val list = Array(1,3,5,2,4,6)
    assert(3 === CountInversion(list))
  }
}