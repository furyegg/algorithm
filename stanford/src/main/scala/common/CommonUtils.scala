package common

object CommonUtils {
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

}