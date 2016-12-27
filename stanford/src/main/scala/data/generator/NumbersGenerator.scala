package data.generator

import java.io.{BufferedOutputStream, FileOutputStream, PrintWriter}

import scala.util.Random


object NumbersGenerator {
  def main(args: Array[String]): Unit = {
    new UniqueRandomNumbersGenerator(100).generate()
  }
}

trait NumbersGenerator {
  def size: Int
  def generate
  def outputFile: String = {
    "src/main/resources/numbers-" + size + ".txt"
  }
  val out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(outputFile)))
}

case class UniqueRandomNumbersGenerator(size: Int) extends NumbersGenerator {
  import scala.collection.mutable.Set
  
  override def generate(): Unit = {
    val numbers = Set[Int]()
    var i = 0
    while (i < size) {
      val n = new Random().nextInt(size) + 1
      numbers += n
      i += 1
    }
    
    for {
      n <- numbers
    } {
      out.write(n.toString + "\n")
    }
    out.flush()
    out.close()
  }

}