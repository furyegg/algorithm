package course1.week3

import scala.util.Sorting
import common.ResourceUtils._

object QuickSort {
  def apply(list: Array[Int], pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = new QuickSort().sort(list, pivot)
  
  def main(args: Array[String]): Unit = {
    val file = "course1/QuickSort.txt"
    
    val list1 = getNumbers(file)
    val res1 = QuickSort(list1, pivot1)
    println(res1._1.mkString(","))
    println(res1._2)
  
    val list2 = getNumbers(file)
    val res2 = QuickSort(list2, pivot2)
    println(res2._1.mkString(","))
    println(res2._2)
  
    val list3 = getNumbers(file)
    val res3 = QuickSort(list3, pivot3)
    println(res3._1.mkString(","))
    println(res3._2)
  }
  
  def pivot1(list: Array[Int], start: Int, end: Int): Int = start
  
  def pivot2(list: Array[Int], start: Int, end: Int): Int = end
  
  def pivot3(list: Array[Int], start: Int, end: Int): Int = {
    val len = end - start
    val m = if (len % 2 == 0) len / 2 - 1 else len / 2
    middle(list, start, start + m, end)
  }
  
  def middle(list: Array[Int], a: Int, b: Int, c: Int): Int = {
    val s = (list(a), a)
    val m = (list(b), b)
    val e = (list(c), c)
    val sorted: Array[(Int, Int)] = Sorting.stableSort(Array(s, m, e), (e1: (Int, Int), e2: (Int, Int)) => e1._1 < e2._1)
    sorted(1)._2
  }
}

class QuickSort {
  def sort(list: Array[Int], pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = {
    sort(list, 0, list.length - 1, pivot)
  }
  
  private def sort(list: Array[Int], start: Int, end: Int, pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = {
    if (start >= end) {
      (list, 0)
    } else if (end - start == 1) {
      compareAndSwap(list, start, end)
      (list, 1)
    } else {
      // println(s"start - end: $start - $end")
      // val res = list.mkString(",")
      // println(s" ===> $res")

      val p = pivot(list, start, end)
  
      println(s"--- start sort at => $start to $end")
      printList(list, start, end, p)
      val partitionIndex = partition(list, start, end, p)
      printList(list, start, end, partitionIndex)
      println
      
      val left = sort(list, start, partitionIndex - 1, pivot)
      val right = sort(list, partitionIndex + 1, end, pivot)

      val currentComp = end - start
      val leftComp = left._2
      val rightComp = right._2
      val totalComp = currentComp + leftComp + rightComp

      // println(s"new comparison: $currentComp, $leftComp, $rightComp, $totalComp")

      (list, totalComp)
    }
  }
  
  private def printList(list: Array[Int], start: Int, end: Int, p: Int): Unit = {
    var i = 0
    var s = ""
    while (i < list.length) {
      val n =
        if (i == p) "[" + list(i) + "]"
        else if (i == start) ">" + list(i)
        else if (i == end) list(i) + "<"
        else list(i)
      
      s += n + " "
      i += 1
    }
    println(s)
  }
  
  def partition(list: Array[Int], start: Int, end: Int, p: Int): Int = {
    if (p == start) partitionAtStart(list, start, end)
    else if (p == end) partitionAtEnd2(list, start, end)
    else partitionAtMid2(list, start, end, p)
  }

  def partitionAtStart(list: Array[Int], start: Int, end: Int): Int = {
    val p = list(start)
    var i = -1
    var j = start + 1
    while (j <= end) {
      if (list(j) > p) {
        if (i < 0) i = j - 1
      } else {
        if (i > -1) {
          i += 1
          swap(list, i, j)
        }
      }
      j +=  1
    }
    if (i > 0) {
      compareAndSwap(list, start, i)
      i
    } else if (list(start) > list(end)) {
      swap(list, start, end)
      end
    } else {
      start
    }
  }
  
  def partitionAtEnd2(list: Array[Int], start: Int, end: Int): Int = {
    swap(list, start, end)
    partitionAtStart(list, start, end)
  }
  
  def partitionAtMid2(list: Array[Int], start: Int, end: Int, p: Int): Int = {
    swap(list, start, p)
    partitionAtStart(list, start, end)
  }
  
  def partitionAtEnd(list: Array[Int], start: Int, end: Int): Int = {
    val p = list(end)
    var i = -1
    var j = start
    while (j <= end - 1) {
      if (list(j) > p) {
        if (i < 0) i = j
      } else {
        if (i > -1) {
          swap(list, i, j)
          i += 1
        }
      }
      j += 1
    }
    if (i > -1) {
      compareAndSwap(list, i, end)
      i
    } else if (list(start) > list(end)) {
      swap(list, start, end)
      start
    } else {
      end
    }
  }
  
  def partitionAtMid(list: Array[Int], start: Int, end: Int, p: Int): Int = {
    val pv = list(p)
    var i = -1
    var j = start
    while (j <= end) {
      if (j == p)
        j += 1
      else {
        if (list(j) > pv) {
          if (i < 0) i = j
        } else {
          if (i > -1) {
            swap(list, i, j)
            i += 1
            if (i == p) i += 1
          }
        }
        j += 1
      }
    }
    if (i == start) {
      swap(list, p, start)
      start
    } else if (i > start) {
      if (i > p) {
        swap(list, p, i - 1)
        i - 1
      } else {
        swap(list, p, i)
        i
      }
    } else {
      p
    }
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