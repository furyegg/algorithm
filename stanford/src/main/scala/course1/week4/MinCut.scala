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
  
  def buildBags(numbersList: Array[Array[Int]]): List[Bag] =
    (for {
      numbers <- numbersList
      bag = numbers.map(n => n.toString).toList
    } yield bag).toList
  
  def getContractTimes(size: Int) = 2000
  
  def findMinCut(g: Graph, edges: Set[Edge]): (Int, Set[Edge]) = {
    if (g.size == 2) (edges.size, edges)
    else {
      val (cg, ces) = contract(g, edges)
      findMinCut(cg, ces)
    }
  }
  
  def contract(g: Graph, edges: Set[Edge]): (Graph, Set[Edge]) = {
    def randomPair = {
      def random = Random.nextInt(g.size)
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
    
    val (i1, i2) = randomPair
    // println(s"chosen: $i1, $i2")
    contract(i1, i2, g, edges)
  }
  
  def contract(i1: Int, i2: Int, g: Graph, edges: Set[Edge]): (Graph, Set[Edge]) = {
    def removeVertices(i1: Int, i2: Int): (Bag, Bag, List[Bag]) = {
      val bag1 = g.bag(i1)
      val bag2 = g.bag(i2)
      val rest1 = CommonUtils.removeAt(g.bags, i1)
      val rest2 = CommonUtils.removeAt(rest1, i2 - 1)
      (bag1, bag2, rest2)
    }
  
    def updateByNewVertex(v: Vertex, bags: List[Bag]): List[Bag] = {
      def removeVertex(vs: List[Vertex], bag: Bag): Bag =
        vs match {
          case Nil => bag
          case x :: xs => removeVertex(xs, CommonUtils.remove(bag, x))
        }
    
      val oldVertices = v.split(sep).toList
      for {
          bag <- bags
          contractedBag = removeVertex(oldVertices, bag)
          updatedBag = if (contractedBag.length != bag.length) contractedBag :+ v else bag
      } yield updatedBag
    }
  
    def createNewBag(newVertex: String, restBags: List[Bag]): Bag = newVertex :: restBags.map(n => n.head)
  
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
  
    val (bag1, bag2, restBags) = removeVertices(i1, i2)
    val v1 = bag1.head
    val v2 = bag2.head
    val newVertex = s"$v1$sep$v2"
    val newBag = createNewBag(newVertex, restBags)
    val updatedBags = updateByNewVertex(newVertex, restBags)
    val restEdges = removeEdges(v1, v2, edges)
    (Graph(newBag :: updatedBags), restEdges)
  }
}