package course1.week1


import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import KaratsubaMuliplication._

@RunWith(classOf[JUnitRunner])
class KaratsubaMuliplicationSuite extends FunSuite {
  
  test("test 1 * 2") {
    assert(1 * 2 === cal(1, 2))
  }
  
  test("test 16789 * 2") {
    assert(16789 * 2 === cal(16789, 2))
  }
  
  test("test 5678 * 1234") {
    assert(5678 * 1234 === cal(5678, 1234))
  }
  
  test("test 15678 * 12341") {
    assert(15678 * 12341 === cal(15678, 12341))
  }
  
  test("test 12341234 * 56785678") {
    assert(BigDecimal(56785678L) * BigDecimal(12341234L) === cal(12341234, 56785678))
  }
  
  test("test two big even numbers") {
    assert(BigDecimal(5678567856785678L) * BigDecimal(1234123412341234L) === cal(5678567856785678L, 1234123412341234L))
  }
  
  test("test 567856781 * 123412341") {
    assert(BigDecimal(567856781L) * BigDecimal(123412341L) === cal(567856781, 123412341))
  }
  
  test("test 178962343243 * 2983274987653") {
    assert(BigDecimal(178962343243L) * BigDecimal(2983274987653L) === cal(178962343243L, 2983274987653L))
  }
  
  test("test very big number") {
    val jn1 = new java.math.BigDecimal("3141592653589793238462643383279502884197169399375105820974944592")
    val jn2 = new java.math.BigDecimal("2718281828459045235360287471352662497757247093699959574966967627")
    val n1 = BigDecimal("3141592653589793238462643383279502884197169399375105820974944592")
    val n2 = BigDecimal("2718281828459045235360287471352662497757247093699959574966967627")
    val expected = jn1.multiply(jn2).toPlainString
    val res = cal(n1, n2).toString.replace(".00", "")
    println(expected)
    println(res)
    assert(expected === res)
  }
  
}