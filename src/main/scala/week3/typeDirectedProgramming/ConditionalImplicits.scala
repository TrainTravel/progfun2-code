package week3.typeDirectedProgramming

import Ordering._

import scala.annotation.tailrec

object ConditionalImplicits {
  // Example: Consider how we order two String values: is "abc" lexicographically before "abd"?
  implicit def orderingList[A](implicit ord: Ordering[A]): Ordering[List[A]] =
    new Ordering[List[A]] {

      @tailrec
      def compare(xs: List[A], ys: List[A]) =
        (xs, ys) match {
//          case (x :: xsTail, y :: ysTail) => if (ord.compare(x, y) == -1) -1 else if (ord.compare(x, y) == 1) 1 else compare(xsTail, ysTail)
          case (x :: xsTail, y :: ysTail) =>
            val c = ord.compare(x, y)
            if( c != 0) c else compare(xsTail, ysTail)
          case (Nil, Nil) => 0 // If this is put at later cases, it would be unreachable code.
          case (_, Nil) => 1
          case (Nil, _) => -1
        }
    }

  def main(args: Array[String]): Unit = {
    val xss = List(List(1, 2, 3), List(1), List(1, 1, 3))
    sort(xss)
    sort[List[Int]](xss)
    sort[List[Int]](xss)(orderingList(Ordering.Int))
    /**
     * We haven’t defined an instance of Ordering[List[Int]] and yet we have been
     * able to sort a list of List[Int] elements!
     * How did the compiler manage to provide such an instance to us?
     * 1. fix type A to List[Int]: sort[List[Int]](xss)
     * 2. search for an implicit definition of type Ordering[List[Int]].
     * 3. It found that our orderingList definition could be a match under the
     *  condition that it could also find an implicit definition of type Ordering[Int],
     *  which it eventually found. Finally, the compiler inserted the following arguments for us:
     *  sort[List[Int]](xss)(orderingList(Ordering.Int))
     *
     *
     * In this case, the compiler combined two implicit definitions (orderingList and Ordering.Int)
     * before terminating. In general, though, an arbitrary number of implicit definitions
     * can be combined until the search hits a “terminal” definition.
     *
     * Consider for instance these four implicit definitions:
     * implicit def a: A = ...
     * implicit def aToB(implicit a: A): B = ...
     * implicit def bToC(implicit b: B): C = ...
     * implicit def cToD(implicit c: C): D = ...
     *
     * We can then ask the compiler to summon a value of type D:
     * implicitly[D]
     *
     * The compiler finds that there is a candidate definition, cToD,
     * that can provide such a D value, under the condition that it
     * can also find an implicit definition of type C. Again, it finds
     * that there is a candidate definition, bToC, that can provide such a C value,
     * under the condition that it can also find an implicit definition of type B.
     * Once again, it finds that there is candidate definition, aToB,
     * that can provide such a B value, under the condition that it can also
     * find an implicit value of type A. Finally, it finds a candidate definition
     * for type A and the algorithm terminates!
     *
     * At the beginning of this lesson, we showed that by using implicit parameters
     * the compiler could infer simple arguments for us. We have now reached a point
     * where we can appreciate that the compiler can infer more complex arguments
     * (by inferring arguments of arguments!).
     *
     * It not only significantly reduces code verbosity, it also alleviates developers
     * from implementing parts of their programs, which are summoned by the compiler
     * based on their type (hence the name “type-directed programming”).
     *
     * In practice, complex fragments of programs such as serializers and
     * deserializers of data types can be summoned by the compiler.
     */

  }
}
