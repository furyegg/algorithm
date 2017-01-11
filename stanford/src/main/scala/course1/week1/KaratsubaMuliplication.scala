package course1.week1

import common.CommonUtils

object KaratsubaMuliplication {
  def cal(n1: BigDecimal, n2: BigDecimal): BigDecimal = {
    val karatsuba = new KaratsubaMuliplication
    karatsuba.cal(n1, n2)
  }
  
  def main(args: Array[String]): Unit = {
    println(CommonUtils.midIdx(5))
    println(5 / 2)
  }
}

/**
  * 10^n * ac + 10^n/2 (ad + bc) + bd
  * ad + bc = (a+b)(c+d) - ac - bd
  */
class KaratsubaMuliplication {
  val shreshold = 4
  
  def cal(n1: BigDecimal, n2: BigDecimal): BigDecimal = {
    val s1 = n1.toString
    val s2 = n2.toString
    println(s"\ncalc: $s1 * $s2")
    
    if (s1.length < shreshold || s2.length < shreshold) n1 * n2
    else {
      val (small, big) = if (n1 < n2) (s2, s1) else (s1, s2)
      val m1 = CommonUtils.midIdx(big.length)
      val m2 = CommonUtils.midIdx(small.length)
      val (a, b) = split(big, m1)
      val (c, d) = split(small, m2)
      
      val ac = cal(a, c)
      val bd = cal(b, d)
      val adbc = cal((a + b), (c + d)) - ac - bd
  
      val pow2 = big.length / 2
      val pow1 = pow2 * 2
      println(s"$a * $c * 10^$pow1 + $adbc * 10^$pow2 + $b * $d")
      ac * math.pow(10, pow1) + adbc * math.pow(10, pow2) + bd
    }
  }
  
  private def split(s: String, m: Int): (BigDecimal, BigDecimal) =
    if (m == 0) (0, BigDecimal(s)) else (BigDecimal(s.take(m + 1)), BigDecimal(s.drop(m + 1)))
}