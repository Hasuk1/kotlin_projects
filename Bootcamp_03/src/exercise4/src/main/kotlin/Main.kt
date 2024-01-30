import bullets.RifleBullet
import bullets.ThreeEighty
import weapons.Revolver

fun main() {
  val player = Player(ThreeEighty::class)
  val ammoRifle = player.equipment.reload(RifleBullet::class)
  val rifle = player.equipment.getTheRifle()
  ammoRifle.forEach { bullet ->
    rifle.load(bullet as RifleBullet)
    rifle.shoot()
  }
  val ammoRevolver = player.equipment.reload(ThreeEighty::class)
  @Suppress("UNCHECKED_CAST")
  val revolver = player.equipment.getTheRevolver() as Revolver<ThreeEighty>
  ammoRevolver.forEach { bullet ->
    revolver.load(bullet as ThreeEighty)
    revolver.shoot()
  }
}