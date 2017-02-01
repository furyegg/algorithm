package common

import scala.util.Random

object CommonUtils {
  
  /**
    * Get middle index of a number, if n is even number, return the left index of the middle, such as 1 of 4.
    */
  def midIdx(n: Int): Int = if (n % 2 == 0) n / 2 - 1 else n / 2
  
  def removeAt[T](l: List[T], i: Int): List[T] = {
    l.take(i) ::: l.drop(i + 1)
  }
  
  def removeAt[T](l: List[T], i1: Int, i2: Int): List[T] = {
    val rest1 = removeAt(l, i1)
    removeAt(rest1, i2 - 1)
  }
  
  def remove[T](l: List[T], value: T): List[T] = {
    l.diff(List(value))
  }
  
  def remove[T](l: List[T], values: T*): List[T] = {
    l.diff(values.toList)
  }
  
  def randomPair(size: Int): (Int, Int) = {
    def randomPair = {
      def random = Random.nextInt(size)
      val first = random
      def findSecond(first: Int): Int = {
        val sec = random
        if (sec != first) sec
        else findSecond(first)
      }
      val second = findSecond(first)
      if (first < second) (first, second)
      else (second, first)
    }
    randomPair
  }

}