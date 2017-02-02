package course1.week4

import course1.week4.Graph._

object Graph {
  def apply(bags: List[Bag]) = new Graph(bags)
  
  type Vertex = String
  type Bag = List[Vertex]
  type Edge = (Vertex, Vertex)
  
  val sep = "-"
  
  def compareVertex(v1: Vertex, v2: Vertex): Boolean = {
    if (v1.contains(sep) || v2.contains(sep)) {
      val v1Sum = v1.split(sep).map(v => v.toInt).sum
      val v2Sum = v2.split(sep).map(v => v.toInt).sum
      v1Sum < v2Sum
    } else
      if (v1.length != v2.length) v1.length < v2.length
      else v1 < v2
  }
  
  def combineVertex(v1: Vertex, v2: Vertex): Vertex = {
    val v1s = v1.split(sep).map(v => v.toInt)
    val v2s = v2.split(sep).map(v => v.toInt)
    v1s.union(v2s).sorted.map(v => v.toString).mkString(sep)
  }
}

class Graph(var bags: List[Bag]) {
  lazy val size = bags.size
  
  lazy val vertices: List[Vertex] = bags.map(b => b.head)
  
  lazy val allEdges: Set[Edge] = {
    def adj(bag: Bag): List[Vertex] = bag.tail
    
    val all = for {
      bag <- bags
      v = bag.head
      adjvs = adj(bag)
      adjv <- adjvs
      if (compareVertex(v, adjv))
    } yield (v, adjv)
    all.toSet
  }
  
  def adj(v: Vertex): List[Vertex] = ???
  
  def bag(i: Int) = bags(i)
  
  override def toString: String =
    bags
      .map(b => b.mkString(","))
      .mkString("\n")
        
}