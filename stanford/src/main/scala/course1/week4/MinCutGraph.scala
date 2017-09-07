package course1.week4

import course1.week4.MinCutGraph._

import scala.collection.immutable.TreeSet

object MinCutGraph {
  def apply(bags: List[Bag]) = new MinCutGraph(bags)
  
  type Vertex = List[Int]
  type Bag = List[Vertex]
  type Edge = (Vertex, Vertex)
  
  val sep = "-"
  
  def compareVertex(v1: Vertex, v2: Vertex): Boolean = {
    if (v1.length != v2.length) v1.length < v2.length
    else v1.sum < v2.sum
  }
  
  def combineVertex(v1: Vertex, v2: Vertex): Vertex = {
    (v1 ::: v2).sorted
  }
}

class MinCutGraph(val bags: List[Bag]) {
  lazy val size = bags.size
  
  lazy val vertices: List[Vertex] = bags.map(b => b.head)
  
  lazy val allEdges: Set[Edge] = {
    def adj(bag: Bag): List[Vertex] = bag.tail
  
    val allEdges: TreeSet[(Vertex, Vertex)] = new TreeSet()
    val all = for {
      bag <- bags
      v = bag.head
      adjvs = adj(bag)
      adjv <- adjvs
      if (compareVertex(v, adjv))
    } yield (v, adjv)
    allEdges ++ all
  }
  
  def adj(v: Vertex): List[Vertex] = ???
  
  def bag(i: Int) = bags(i)
  
  override def toString: String =
    bags
      .map(b => b.mkString(","))
      .mkString("\n")
  
}