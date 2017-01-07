package course1.week4

import course1.week4.Graph._

object Graph {
  def apply(bags: List[Bag]) = new Graph(bags)
  
  type Vertex = String
  type Bag = List[Vertex]
  type Edge = (Vertex, Vertex)
  
  val sep = "-"
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
      if (v < adjv)
    } yield (v, adjv)
    all.toSet
  }
  
  def adj(v: Vertex): List[Vertex] = ???
  
  def bag(i: Int) = bags(i)
  
  override def toString: String =
    bags.map(l => l.mkString(","))
      .mkString("; ")
        
}