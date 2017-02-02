package course1.week4

import common.TestResourceUtils
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import MinCut._
import Graph._

@RunWith(classOf[JUnitRunner])
class MinCutSuite extends FunSuite {
  trait GraphSets {
    def newGraph(file: String) = {
      val list = TestResourceUtils.getNumbersList(file)
      Graph(buildBags(list))
    }
    
    val g0 = newGraph("course1/mincut0.txt")
    val g1 = newGraph("course1/mincut1.txt")
    val g1_1 = newGraph("course1/mincut1-1.txt")
    val g2 = newGraph("course1/mincut2.txt")
    val g3 = newGraph("course1/mincut3.txt")
    val g4 = newGraph("course1/mincut4.txt")
    
    val times = 500
    def run(g: Graph, f: (Graph, Set[Edge]) => (Int, Set[Edge])) = {
      var i = 0
      var min = Int.MaxValue
      var res: (Int, Set[Edge]) = null
      // println("Graph:\n" + g)
      while (i < times) {
        // println("==================================================================")
        val (count, edges) = f(g, g.allEdges)
        if (count < min) {
          min = count
          res = (count, edges)
        }
        i += 1
      }
      res
    }
  }
  
  test("create graph") {
    new GraphSets {
      assert(g0.toString === "1,2,3\n2,1,3,4\n3,1,2,4\n4,2,3")
    }
  }
  
  test("contract with fixed vertices") {
    new GraphSets {
      val (g, edges) = contract(("1","2"), g0, g0.allEdges)
      assert(g.toString === "1-2,3,4\n3,4,1-2\n4,3,1-2")
    }
  }
  
  test("find min cut for graph0") {
    new GraphSets {
      val (count, edges) = run(g0, findMinCut)
      println(count)
      println(edges)
    }
  }
  
  test("find min cut for graph1") {
    new GraphSets {
      val (count, edges) = run(g1, findMinCut)
      assert(count === 2)
      assert(edges.toString === Set((1,7), (4,5)).toString)
    }
  }
  
  test("find min cut for graph2") {
    new GraphSets {
      val (count, edges) = run(g2, findMinCut)
      assert(count === 2, "edges: " + edges.toString)
      assert(edges.toString === Set((1,7), (4,5)).toString)
    }
  }
  
  test("find min cut for graph3") {
    new GraphSets {
      val (count, edges) = run(g3, findMinCut)
      // assert(count === 1, "edges: " + edges.toString)
      println(edges.toString)
    }
  }
  
  test("find min cut for graph4") {
    new GraphSets {
      val (count, edges) = run(g4, findMinCut)
      // assert(count === 1, "edges: " + edges.toString)
      println(count)
      println(edges.toString)
    }
  }
  
  test("find min cut for graph1-1") {
    new GraphSets {
      val (count, edges) = run(g1_1, findMinCut)
      println(count)
      println(edges.toString)
    }
  }
}