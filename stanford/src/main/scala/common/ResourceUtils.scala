package common

import org.apache.commons.lang3.StringUtils

import scala.io.Source
import scala.reflect.ClassTag

object ResourceUtils {
  implicit val isTestResource = false
  
  def getLines[T: ClassTag](file: String, f: String => T)(implicit isTestResource: Boolean): Array[T] = {
    val path = if (isTestResource) "src/test/resources/" else "src/main/resources/"
    (for {
        line <- Source.fromFile(path + file).getLines()
        if (StringUtils.isNoneBlank(line))
      } yield f(line)
    ).toArray
  }
  
  def getNumbers(file: String): Array[Int] = getLines(file, line => line.toInt)
  
  def getNumbersList(file: String): Array[Array[Int]] =
    getLines(
      file,
      line =>
        line.split(Array('\t',' '))
          .filter(n => StringUtils.isNoneBlank(n))
          .map(n => n.toInt)
    )
  
}