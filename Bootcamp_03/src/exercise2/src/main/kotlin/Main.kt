import bullets.FortyFive
import bullets.ThreeEighty
import revolver.Revolver

fun main() {
  val rev=Revolver<ThreeEighty>()
  val bullet1 = ThreeEighty()
  rev.load(bullet1)
  rev.load(ThreeEighty())
  rev.load(bullet1)
  println(rev)
}