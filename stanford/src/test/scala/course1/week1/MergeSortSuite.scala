package course1.week1

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.util.Sorting

@RunWith(classOf[JUnitRunner])
class MergeSortSuite extends FunSuite{
  test("test1") {
    val list = Array(1,3,5,36,57,8,98,23,4,34,6,46)
    val expected: Array[Int] = Sorting.stableSort(list)
    assert(expected === MergeSort(list))
  }
}