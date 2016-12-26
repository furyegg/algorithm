package course1.week3

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class QuickSortSuite extends FunSuite {
  test("test1") {
    val list = Array(1,3,5,2,4,6,5,7,88,9,19,43,23,3,83,123,21,312,31,23,21,33)
    val res1 = QuickSort(list, QuickSort.pivot1)
    println(res1._1.mkString(","))
    println(res1._2)

    val res2 = QuickSort(list, QuickSort.pivot2)
    println(res2._1.mkString(","))
    println(res2._2)

    val res3 = QuickSort(list, QuickSort.pivot3)
    println(res3._1.mkString(","))
    println(res3._2)
  }
  
  test("test2") {
    val list = Array(3,8,2,5,1,4,7,6)
    val res1 = QuickSort(list, QuickSort.pivot1)
    assert("12345678" === res1._1.mkString(""))
    println(res1._2)

    val res2 = QuickSort(list, QuickSort.pivot2)
    assert("12345678" === res2._1.mkString(""))
    println(res2._2)

    val res3 = QuickSort(list, QuickSort.pivot3)
    assert("12345678" === res3._1.mkString(""))
    println(res3._2)
  }
  
  test("test3") {
    val list = Array(1,2,3,4,5,6,7,8)
    val res1 = QuickSort(list, QuickSort.pivot1)
    assert("12345678" === res1._1.mkString(""))
    println(res1._2)

    val res2 = QuickSort(list, QuickSort.pivot2)
    assert("12345678" === res2._1.mkString(""))
    println(res2._2)

    val res3 = QuickSort(list, QuickSort.pivot3)
    assert("12345678" === res3._1.mkString(""))
    println(res3._2)
  }
  
  test("test4") {
    val list = Array(8,7,6,5,4,3,2,1)
    val res1 = QuickSort(list, QuickSort.pivot1)
    assert("12345678" === res1._1.mkString(""))
    println(res1._2)

    val res2 = QuickSort(list, QuickSort.pivot2)
    assert("12345678" === res2._1.mkString(""))
    println(res2._2)

    val res3 = QuickSort(list, QuickSort.pivot3)
    assert("12345678" === res3._1.mkString(""))
    println(res3._2)
  }
}