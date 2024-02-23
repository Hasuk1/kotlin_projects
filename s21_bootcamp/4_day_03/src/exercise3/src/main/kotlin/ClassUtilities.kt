import bullets.Bullet
import bullets.FortyFive
import revolver.Revolver
import revolver.RevolverDrum

fun <T : Bullet> Revolver<T>.replaceBullets() {
  val bullets = drum.list.filterNotNull().toMutableList()
  drum.list.clear()
  for (i in 0 until 6) {
    if (i < bullets.size) {
      drum.list.add(bullets[i])
    } else {
      drum.list.add(null)
    }
  }
  drum.index = RevolverDrum.Index(0)
}

fun <T : Bullet> Revolver<T>.russianRoulette(){
  scroll()
  shoot()
}

fun <T : Bullet> Revolver<T>.reloadToNewDrum():Revolver<T>{
  val rev = Revolver<T>()
  if (getCurrentCartridge() != null) {
    val bullet = extract()?: return rev
    bullet.extract()
    rev.load(bullet)
  }
  return rev
}