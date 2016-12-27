package course1.week3

import scala.io.Source
import scala.util.Sorting

object QuickSort {
  def apply(list: Array[Int], pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = new QuickSort().sort(list, 0, pivot)
  
  private def getNumbers(): Array[Int] =
    (for {
        line <- Source.fromFile("src/main/resources/QuickSort.txt").getLines()
//        line <- Source.fromFile("src/main/resources/numbers-100.txt").getLines()
      } yield line.toInt
    ).toArray
  
  def main(args: Array[String]): Unit = {
    val list = getNumbers()
//    val res1 = QuickSort(list, pivot1)
//    println(res1._1.mkString(","))
//    println(res1._2)
    val res2 = QuickSort(list, pivot2)
    println(res2._1.mkString(","))
    println(res2._2)
//    val res3 = QuickSort(list, pivot3)
//    println(res3._1.mkString(","))
//    println(res3._2)
  }
  
  def pivot1(list: Array[Int], start: Int, end: Int): Int = start
  
  def pivot2(list: Array[Int], start: Int, end: Int): Int = end
  
  def pivot3(list: Array[Int], start: Int, end: Int): Int = {
    val len = list.length
    val m = if (len % 2 == 0) len / 2 - 1 else len / 2
    middle(list, start, m, end)
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
  def sort(list: Array[Int], comparison: Int, pivot: (Array[Int], Int, Int) => Int): (Array[Int], Int) = {
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
  
//      println(s"--- start sort at => $start to $end")
//      printList(list, start, end, p)
      val partitionIndex = partition(list, start, end, p)
//      printList(list, start, end, partitionIndex)
//      println
      
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
    else if (p == end) partitionAtEnd(list, start, end)
    else partitionAtMid(list, start, end, p)
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
          if (i < 0 && j < p) i = j
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
      swap(list, p, i - 1)
      i - 1
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
  
  def partition2(list: Array[Int], start: Int, end: Int, p: Int): Int = {
    val pValue = list(start)
    var i = -1
    var j = start
    while (j <= end) {
      if (j == p)
        j += 1
      else {
        if (list(j) > pValue) {
          if (i < 0) i = j - 1
          j += 1
        } else {
          if (i > -1) {
            i += 1
            if (i == p) i += 1
            swap(list, i, j)
          }
          j += 1
        }
      }
    }
    if (i > 0) {
      compareAndSwap(list, p, i)
      i
    } else if (list(start) > list(end)) {
      swap(list, start, end)
      end
    } else {
      p
    }
  }
  
}