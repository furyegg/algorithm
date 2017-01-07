package course1.week3

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import common.TestResourceUtils._
import QuickSort._

@RunWith(classOf[JUnitRunner])
class QuickSortSuite extends FunSuite {
  
  test("test1") {
    val list = Array(1,3,5,2,4,6,5,7,88,9,19,43,23,3,83,123,21,312,31,23,21,33)
//    val res1 = QuickSort(list, QuickSort.pivot1)
//    println(res1._1.mkString(","))
//    println(res1._2)
//
//    val res2 = QuickSort(list, QuickSort.pivot2)
//    println(res2._1.mkString(","))
//    println(res2._2)
//
    val res3 = QuickSort(list, QuickSort.pivot3)
    println(res3._1.mkString(","))
    println(res3._2)
  }
  
  test("test2") {
    val list = Array(3,8,2,5,1,4,7,6)
//    val res1 = QuickSort(list, QuickSort.pivot1)
//    assert("12345678" === res1._1.mkString(""))
//    println(res1._2)

//    val res2 = QuickSort(list, QuickSort.pivot2)
//    assert("12345678" === res2._1.mkString(""))
//    println(res2._2)

    val res3 = QuickSort(list, QuickSort.pivot3)
    assert("12345678" === res3._1.mkString(""))
    println(res3._2)
  }
  
  test("test3") {
    val list = Array(1,2,3,4,5,6,7,8)
//    val res1 = QuickSort(list, QuickSort.pivot1)
//    assert("12345678" === res1._1.mkString(""))
//    println(res1._2)

//    val res2 = QuickSort(list, QuickSort.pivot2)
//    assert("12345678" === res2._1.mkString(""))
//    println(res2._2)

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
  
  test("test5") {
    val file = "course1/10.txt"
//    val list = getNumbers("10.txt")
//    val res1 = QuickSort(list, pivot1)
//    println(res1._1.mkString(","))
//    assert(res1._2 === 25)

    val list2 = getNumbers(file)
    val res2 = QuickSort(list2, pivot2)
    println(res2._1.mkString(","))
    assert(res2._2 === 29)
  
//    val list3 = getNumbers(file)
//    val res3 = QuickSort(list3, pivot3)
//    println(res3._1.mkString(","))
//    assert(res3._2 === 21)
  }
  
  test("test6") {
//    val list = getNumbers("100.txt")
//    val res1 = QuickSort(list, pivot1)
//    println(res1._1.mkString(","))
//    assert(res1._2 === 615)

    val list2 = getNumbers("course1/100.txt")
    val res2 = QuickSort(list2, pivot2)
    println(res2._1.mkString(","))
    assert(res2._2 === 587)
    
//    val list3 = getNumbers("100.txt")
//    val res3 = QuickSort(list3, pivot3)
//    println(res3._1.mkString(","))
//    assert(res3._2 === 518)
  }
  
  test("test6-1") {
    val list = Array(3, 9, 8, 4, 6, 10, 2, 5, 7, 1)
    val res2 = QuickSort(list, pivot2)
    println(res2._1.mkString(","))
    println(res2._2)
    // assert(res2._2 === 10184)
  }
  
  test("test7") {
    val file = "course1/1000.txt"
//    val list = getNumbers(file)
//    val res1 = QuickSort(list, pivot1)
//    println(res1._1.mkString(","))
//    assert(res1._2 === 10297)
    
    val list2 = getNumbers(file)
    val res2 = QuickSort(list2, pivot2)
    println(res2._1.mkString(","))
    assert(res2._2 === 10184)
//
//    val list3 = getNumbers(file)
//    val res3 = QuickSort(list3, pivot3)
//    println(res3._1.mkString(","))
//    assert(res3._2 === 8921)
  }
}