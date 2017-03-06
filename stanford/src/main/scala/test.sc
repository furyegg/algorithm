import scala.util.Random

val l1 = List(1,2,3)
val l2 = List(11,2,3)
val l3 = List(111,2,3)
val list = List(l1,l2,l3)
list.filter(l => l != l1 && l != l2)

for {
  i <- 1 to 100
} {
  val r = new Random
  println(r.nextInt(100))
}