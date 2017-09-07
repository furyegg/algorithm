package course1.week4

object GraphQuickFind {
  def apply(links: Array[(Int, Int)]): GraphQuickFind = ???
}

class GraphQuickFind(n: Int) {
  private val id: Array[Int] = (0 until n).toArray
  
  var count = n
  
  def find(p: Int): Int = id(p)
  
  def connected(p: Int, q: Int): Boolean = find(p) == find(q)
  
  def union(p: Int, q: Int): Unit = {
    val pId = find(p)
    val qId = find(q)
    
    if (pId != qId) {
      var i = 0
      while (i < id.length) {
        if (id(i) == qId) id(i) = pId
        i = i + 1
        count = count - 1
      }
    }
  }
  
}