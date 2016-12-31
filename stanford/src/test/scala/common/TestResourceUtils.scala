package common

import scala.io.Source
import scala.reflect.ClassTag

object TestResourceUtils {
  implicit val isTestResource = true
  
  def getLines[T: ClassTag](file: String, f: String => T)(implicit isTestResource: Boolean): Array[T] = {
    val path = if (isTestResource) "src/test/resources/" else "src/main/resources/"
    (for {
        line <- Source.fromFile(path + file).getLines()
      } yield f(line)
    ).toArray
  }
  
  def getNumbers(file: String): Array[Int] = getLines(file, line => line.toInt)
  
}