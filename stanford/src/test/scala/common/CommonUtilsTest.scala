package common

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CommonUtilsTest extends FunSuite {
  test("remove test") {
    val list = List(1,2,3,4,5)
    assert(CommonUtils.remove(list, 1) === List(2,3,4,5))
    assert(CommonUtils.remove(list, 1,2) === List(3,4,5))
    assert(CommonUtils.remove(list, 1,8) === List(2,3,4,5))
    assert(CommonUtils.remove(list, 7,8) === List(1,2,3,4,5))
  }
}