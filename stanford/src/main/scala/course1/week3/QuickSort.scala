package course1.week3

import scala.io.Source
import scala.util.Sorting

object QuickSort {
  def apply(list: Array[Int], pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = new QuickSort().sort(list, 0, pivot)
  
  private def getNumbers(): Array[Int] =
    (for {
        line <- Source.fromFile("src/main/resources/QuickSort.txt").getLines()
      } yield line.toInt
    ).toArray
  
  def main(args: Array[String]): Unit = {
    val list = getNumbers()
    val res1 = QuickSort(list, pivot1)
    println(res1._1)
    println(res1._2)
//    val res2 = QuickSort(list, pivot2)
//    println(res2._2)
//    val res3 = QuickSort(list, pivot3)
//    println(res3._2)
  }
  
  def pivot1(list: Array[Int], start: Int, end: Int): Int = start
  
  def pivot2(list: Array[Int], start: Int, end: Int): Int = end
  
  def pivot3(list: Array[Int], start: Int, end: Int): Int = {
    val len = list.length
    val m = if (len % 2 == 0) len / 2 - 1 else len / 2
    val mid = list(m)
    
    middle(list, start, mid, end)
  }
  
  def middle(list: Array[Int], a: Int, b: Int, c: Int): Int = {
    val s = (list(a), a)
    val m = (list(b), b)
    val e = (list(c), c)
    
    val arr = Array(s, m, e)
    val sorted: Array[(Int, Int)] = Sorting.stableSort(arr, (e1: (Int, Int), e2: (Int, Int)) => e1._1 < e2._1)
    sorted(1)._2
  }
}

class QuickSort {
  def sort(list: Array[Int], comparison: Int, pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = {
    sort(list, 0, list.length - 1, comparison, pivot)
  }
  
  private def sort(list: Array[Int], start: Int, end: Int, comparison: Int, pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = {
    if (start >= end) {
      (list, comparison)
    } else if (end - start == 1) {
      compareAndSwap(list, start, end)
      (list, 1 + comparison)
    } else {
      println(s"----------- start sort at => $start to $end")
      val p = pivot(list, start, end)
      
      val partitionIndex = partition(list, start, end, p)
      
      val left = sort(list, start, partitionIndex - 1, comparison, pivot)
      val right = sort(list, partitionIndex + 1, end, comparison, pivot)
      
      (list, end - start - 1 + left._2 + right._2)
    }
  }
  
  def partition(list: Array[Int], start: Int, end: Int, p: Int): Int = {
    if (p == start) partitionAtStart(list, start, end)
    else if (p == end) partitionAtStart(list, start, end)
    else partitionAtMid(list, start, end)
  }
  
  def partitionAtStart(list: Array[Int], start: Int, end: Int): Int = {
    println(s"start partition at $start to $end")
    val p = list(start)
    var i = -1
    var j = start + 1
    while (j <= end) {
      if (list(j) > p) {
        println(s"found bigger than pivot, i: $i, j: $j")
        if (i < 0) i = j - 1
        j +=  1
        println(s"after increase, i: $i, j: $j")
      } else {
        println(s"found smaller than pivot, i: $i, j: $j")
        if (i > -1) {
          i += 1
          swap(list, i, j)
        }
        j += 1
        println(s"after increase, i: $i, j: $j")
      }
    }
    if (i > 0) {
      compareAndSwap(list, start, i)
      i
    } else {
      compareAndSwap(list, start, end)
      start
    }
  }
  
  def partitionAtEnd(list: Array[Int], start: Int, end: Int): Int = {
    0
  }
  
  def partitionAtMid(list: Array[Int], start: Int, end: Int): Int = {
    0
  }
  
  private def compareAndSwap(list: Array[Int], i: Int, j: Int) = {
    if (list(i) > list(j)) {
      swap(list, i, j)
    }
  }
  
  private def swap(list: Array[Int], i: Int, j: Int) = {
    val a = list(i)
    val b = list(j)
    val sum = a + b
    list(i) = sum - a
    list(j) = sum - b
  }
  
}