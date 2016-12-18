package course1.week1

import scala.io.Source
import scala.util.Sorting
import org.scalameter._

object MergeSortRunner {
  
  val standardConfig = config(
    Key.exec.minWarmupRuns -> 20,
    Key.exec.maxWarmupRuns -> 40,
    Key.exec.benchRuns -> 10,
    Key.verbose -> false
  ) withWarmer(new Warmer.Default)
  
  def main(args: Array[String]): Unit = {
    val numbers = MergeSort.getNumbers()
    
    val threshold1 = 2
    val time1 = standardConfig measure {
      MergeSort(numbers, threshold1)
    }
    println(s"time for threshold ($threshold1) : $time1 ms")
    
    val threshold2 = 100
    val time2 = standardConfig measure {
      MergeSort(numbers, threshold2)
    }
    println(s"time for threshold ($threshold2) : $time2 ms")
    
    val threshold3 = 1000
    val time3 = standardConfig measure {
      MergeSort(numbers, threshold3)
    }
    println(s"time for threshold ($threshold3) : $time3 ms")
  }
  
}

object MergeSort {
  def apply(list: Array[Int], threshold: Int = 10): Array[Int] = new MergeSort().sort(list, threshold)
  
  def getNumbers(): Array[Int] =
    (for {
//        line <- Source.fromInputStream(this.getClass.getResourceAsStream("IntegerArray.txt")).getLines()
        line <- Source.fromFile("src/main/resources/IntegerArray.txt").getLines()
      } yield line.toInt
    ).toArray
  
  def main(args: Array[String]): Unit = {
    val list = getNumbers()
    val sorted = MergeSort(list)
    println(sorted.mkString(","))
  }
}

class MergeSort {
  def sort(list: Array[Int], threshold: Int): Array[Int] = {
    def merge(l: Array[Int], r: Array[Int]): Array[Int] = {
      val res = new Array[Int](l.length + r.length)
      var i = 0
      var j = 0
      var mergeIndex = i + j
      while (mergeIndex < list.length) {
        if (l.isDefinedAt(i) && r.isDefinedAt(j)) {
          if (l(i) < r(j)) {
            res(mergeIndex) = l(i)
            i += 1
          } else {
            res(mergeIndex) = r(j)
            j += 1
          }
        } else {
          if (l.isDefinedAt(i)) {
            res(mergeIndex) = l(i)
            i += 1
          }
          if (r.isDefinedAt(j)) {
            res(mergeIndex) = r(j)
            j += 1
          }
        }
        mergeIndex = i + j
      }
      res
    }
    
    if (list.length < threshold)
      Sorting.stableSort(list)
    else {
      val mid = list.length / 2
      val (l, r) = list.splitAt(mid)
      merge(sort(l, threshold), sort(r, threshold))
    }
  }
  
}
