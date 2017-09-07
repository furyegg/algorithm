package course1.week4

import common.{CommonUtils, ResourceUtils}
import MinCutGraph._

import scala.util.{Random, Sorting}

object MinCut {
  
  def main(args: Array[String]): Unit = {
    val numbersList = ResourceUtils.getNumbersList("course1/kargerMinCut.txt")
    val bags = buildBags(numbersList)
    
    val g = MinCutGraph(bags)
    val times = getContractTimes(g.size)
  
    val res = (
      for {
        i <- (0 until times).par
        (count, edges) = findMinCut(g, g.allEdges)
        if (count < 30)
      } yield (count, edges)
      ).toArray
    
    Sorting.stableSort(res, (e1: (Int, Set[Edge]), e2: (Int, Set[Edge])) => e1._1 < e2._1)
    
    val list = List.concat(res)
    list.take(10).foreach(println)
  }
  
  def getContractTimes(size: Int) = 1
  
  def buildBags(numbersList: Array[Array[Int]]): List[Bag] =
    (for {
      numbers <- numbersList
      bag = numbers.map(n => List(n)).toList
    } yield bag).toList
  
  def findMinCut(g: MinCutGraph, edges: Set[Edge]): (Int, Set[Edge]) = {
    if (g.size == 2) (edges.size, edges)
    else {
      val (cg, ces) = contract(g, edges)
      // println(cg + "\n")
      findMinCut(cg, ces)
    }
  }
  
  def contract(g: MinCutGraph, edges: Set[Edge]): (MinCutGraph, Set[Edge]) = {
    val allEdges = g.allEdges.toList
//    println("all edges: " + allEdges.length + ", " + allEdges.mkString(", "))
    val random = new Random().nextInt(allEdges.size)
    val edge = allEdges(random)
//    println(s"random: ${random}, contract edge: ${edge}")
    contract(edge, g, edges)
  }
  
  def contract(edge: Edge, g: MinCutGraph, edges: Set[Edge]): (MinCutGraph, Set[Edge]) = {
    def removeVertices(edge: Edge): (Bag, Bag, List[Bag]) = {
      val bag1 = g.bags.filter(b => b.head == edge._1).head
      val bag2 = g.bags.filter(b => b.head == edge._2).head
      val rest = g.bags.filter(b => b != bag1 && b != bag2)
      (bag1, bag2, rest)
    }
  
    def updateRestBags(v1: Vertex, v2: Vertex, newV: Vertex, bags: List[Bag]): List[Bag] =
      (for {
          bag <- bags.par
          contractedBag = CommonUtils.remove(bag, v1, v2)
          updatedBag = if (contractedBag.length != bag.length) contractedBag :+ newV else bag
      } yield updatedBag).toList
  
    def createNewBag(newVertex: Vertex, bag1: Bag, bag2: Bag): Bag = {
      val merged = bag1.tail.union(bag2.tail).toSet
      newVertex :: merged.toList
    }
  
    def removeEdges(v1: Vertex, v2: Vertex, edges: Set[Edge]): Set[Edge] = {
      var es = edges
      for {
        n1 <- v1
        n2 <- v2
        edge = if (n1 < n2) (List(n1), List(n2)) else (List(n2), List(n1))
      } es -= edge
      es
    }
  
    val (bag1, bag2, restBags) = removeVertices(edge)
    val v1 = bag1.head
    val v2 = bag2.head
    val newVertex = MinCutGraph.combineVertex(v1, v2)
    val newBag = createNewBag(newVertex, bag1, bag2)
    val updatedBags = updateRestBags(v1, v2, newVertex, restBags)
    val restEdges = removeEdges(v1, v2, edges)
    // println("after remove edges: " + restEdges)
    (MinCutGraph(newBag :: updatedBags), restEdges)
  }
}