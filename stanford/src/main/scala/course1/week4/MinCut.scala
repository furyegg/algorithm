package course1.week4

import common.{CommonUtils, ResourceUtils}
import Graph._

import scala.util.{Random, Sorting}

object MinCut {
  
  def main(args: Array[String]): Unit = {
    val numbersList = ResourceUtils.getNumbersList("course1/kargerMinCut.txt")
    val bags = buildBags(numbersList)
    
    val g = Graph(bags)
    val times = getContractTimes(g.size)
    val res: Array[(Int, Set[Edge])] = new Array(times)
    var i = 0
    while (i < times) {
      res(i) = findMinCut(g, g.allEdges)
      i += 1
      println(i)
    }
    Sorting.stableSort(res, (e1: (Int, Set[Edge]), e2: (Int, Set[Edge])) => e1._1 < e2._1)
    
    val list = List.concat(res)
    list.take(10).foreach(println)
  }
  
  def getContractTimes(size: Int) = 2000
  
  def buildBags(numbersList: Array[Array[Int]]): List[Bag] =
    (for {
      numbers <- numbersList
      bag = numbers.map(n => n.toString).toList
    } yield bag).toList
  
  def findMinCut(g: Graph, edges: Set[Edge]): (Int, Set[Edge]) = {
    if (g.size == 2) (edges.size, edges)
    else {
      val (cg, ces) = contract(g, edges)
      println(cg + "\n")
      findMinCut(cg, ces)
    }
  }
  
  def contract(g: Graph, edges: Set[Edge]): (Graph, Set[Edge]) = {
    val allEdges = g.allEdges.toList
    println("all edges: " + allEdges.length + ", " + allEdges.mkString(", "))
    val random = Random.nextInt(allEdges.size)
    val edge = allEdges(random)
    println("contract edge: " + edge)
    contract(edge, g, edges)
  }
  
  def contract(edge: Edge, g: Graph, edges: Set[Edge]): (Graph, Set[Edge]) = {
    def removeVertices(edge: Edge): (Bag, Bag, List[Bag]) = {
      val bag1 = g.bags.filter(b => b.head == edge._1).head
      val bag2 = g.bags.filter(b => b.head == edge._2).head
      val rest = g.bags.filter(b => b != bag1 && b != bag2)
      (bag1, bag2, rest)
    }
  
    def updateRestBags(v1: Vertex, v2: Vertex, newV: Vertex, bags: List[Bag]): List[Bag] =
      for {
          bag <- bags
          contractedBag = CommonUtils.remove(bag, v1, v2)
          updatedBag = if (contractedBag.length != bag.length) contractedBag :+ newV else bag
      } yield updatedBag
  
    def createNewBag(newVertex: String, bag1: Bag, bag2: Bag): Bag = {
      var merged = bag1.union(bag2).toSet
      merged -= bag1.head
      merged -= bag2.head
      newVertex :: merged.toList
    }
  
    def removeEdges(v1: Vertex, v2: Vertex, edges: Set[Edge]): Set[Edge] = {
      val v1s = v1.split(sep)
      val v2s = v2.split(sep)
      var es = edges
      for {
        v1 <- v1s
        v2 <- v2s
        edge = if (v1 < v2) (v1, v2) else (v2, v1)
      } es -= edge
      es
    }
  
    val (bag1, bag2, restBags) = removeVertices(edge)
    val v1 = bag1.head
    val v2 = bag2.head
    val newVertex = String.format("%s%s%s", v1, sep, v2)
    val newBag = createNewBag(newVertex, bag1, bag2)
    val updatedBags = updateRestBags(v1, v2, newVertex, restBags)
    val restEdges = removeEdges(v1, v2, edges)
    (Graph(newBag :: updatedBags), restEdges)
  }
}