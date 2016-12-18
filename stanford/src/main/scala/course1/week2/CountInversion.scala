package course1.week2

import course1.week1.MergeSort

import scala.io.Source

object CountInversion {
  def apply(list: Array[Int]): Long = new CountInversion().count(list)
  
  def getNumbers(): Array[Int] =
    (for {
        line <- Source.fromFile("src/main/resources/IntegerArray.txt").getLines()
      } yield line.toInt
    ).toArray
  
  def main(args: Array[String]): Unit = {
    val list = getNumbers()
    val count = CountInversion(list)
    println(count)
  }
}

class CountInversion {
  def splitCount(left: Array[Int], right: Array[Int]): Long = {
    val l = MergeSort(left)
    val r = MergeSort(right)
  
    val len = left.length + right.length
    var i = 0
    var j = 0
    var mergeIndex = i + j
    var count = 0
    
    while (mergeIndex < len) {
      if (l.isDefinedAt(i) && r.isDefinedAt(j)) {
        if (l(i) < r(j)) {
          i += 1
        } else {
          count += l.length - i
          j += 1
        }
      } else {
        i = len
      }
      mergeIndex = i + j
    }
    
    count
  }
  
  def count(list: Array[Int]): Long = {
    if (list.length == 1)
      0
    else if (list.length == 2)
      if (list(0) > list(1)) 1 else 0
    else {
      val mid = list.length / 2
      val (l, r) = list.splitAt(mid)
      val countLeft = count(l)
      val countRight = count(r)
      val countSplit = splitCount(l, r)
      countLeft + countRight + countSplit
    }
  }
}