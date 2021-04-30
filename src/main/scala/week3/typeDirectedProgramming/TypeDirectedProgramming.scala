package week3.typeDirectedProgramming

// We say that Ordering is a type class.
trait Ordering[A] {
  def compare(a1: A, a2: A): Int
}

/**
 * "Ordering.Int", "Ordering.String" are implicit instances of that type for concrete types A, and implicit parameters of type Ordering[A]:
 */
object Ordering {
  implicit val Int: Ordering[Int] =
    new Ordering[Int] {
      def compare(x: Int, y: Int) = if (x < y) -1 else if (x > y) 1 else 0
    }
  implicit val String: Ordering[String] =
    new Ordering[String] {
      def compare(s: String, t: String) = s.compareTo(t)
    }

  /**
   * The method sort can be called with lists of any type A which has an implicit value of type Ordering[A]
   */
  // def sort[A](xs: List[A])(implicit ord: Ordering[A])
  def sort[A: Ordering](xs: List[A]): List[A] = ???
}

// Retroactive Extension

/** A rational number
 *
 * @param num   Numerator
 * @param denom Denominator
 */
case class Rational(num: Int, denom: Int)

object RationalOrdering {
  implicit val orderingRational: Ordering[Rational] =
    new Ordering[Rational] {
      override def compare(a1: Rational, a2: Rational): Int = a1.num * a2.denom - a2.num * a1.denom
    }
}

trait Show[A] {
  def apply(a: A): String
}

object Show {
  implicit val showInt: Show[Int] = new Show[Int] {
    def apply(n: Int): String = s"Int($n)"
  }
}

object TypeDirectedProgramming extends App {
  //  def implicitly[A](implicit value: A): A = value

  implicitly[Show[Int]](Show.showInt)

}
