
package ammonite
package $file.src.main.scala.week2
import _root_.ammonite.interp.api.InterpBridge.{
  value => interp
}
import _root_.ammonite.interp.api.InterpBridge.value.{
  exit
}
import _root_.ammonite.interp.api.IvyConstructor.{
  ArtifactIdExt,
  GroupIdExt
}
import _root_.ammonite.compiler.CompilerExtensions.{
  CompilerInterpAPIExtensions,
  CompilerReplAPIExtensions
}
import _root_.ammonite.runtime.tools.{
  browse,
  grep,
  time,
  tail
}
import _root_.ammonite.compiler.tools.{
  desugar,
  source
}
import _root_.mainargs.{
  arg,
  main
}
import _root_.ammonite.repl.tools.Util.{
  PathRead
}


object streams{
/*<script>*/object streams {
  val xs = Stream.cons(1, Stream.cons(2, Stream.empty))
  Stream(1, 2, 3)

  1 #:: 2 #:: Stream.empty

  (1 to 1000).toStream


  def streamRange(lo: Int, hi: Int): Stream[Int] = {
    print(lo + " ")
    if (lo >= hi) Stream.empty
    else Stream.cons(lo, streamRange(lo + 1, hi))
  }

  def listRange(lo: Int, hi: Int): List[Int] =
    if (lo >= hi) Nil
    else lo :: listRange(lo + 1, hi)

  def isPrime(i: Int): Boolean =
    (2 until i) forall (i % _ != 0)

  ((1000 to 10000).toStream filter isPrime) (1)

  streamRange(1, 10).take(3).toList
}/*</script>*/ /*<generated>*/
def $main() = { scala.Iterator[String]() }
  override def toString = "streams"
  /*</generated>*/
}
