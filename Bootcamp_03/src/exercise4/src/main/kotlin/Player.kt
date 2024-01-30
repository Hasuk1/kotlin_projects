import bullets.*
import weapons.Revolver
import weapons.Rifle
import kotlin.reflect.KClass

class Player <T : Bullet>(revolverCaliber: KClass<T>){
  val equipment = Equipment(revolverCaliber)

  class Equipment <T : Bullet>(revolverCaliber: KClass<T>){
    private val ammoOfTwentyTwo = MutableList(10) { TwentyTwo() }
    private val ammoOfThreeEighty = MutableList(7) { ThreeEighty() }
    private val ammoOfFortyFive = MutableList(44) { FortyFive() }
    private val ammoOfRifleBullet = MutableList(12) { RifleBullet() }
    private val revolver = when (revolverCaliber){
      TwentyTwo::class -> Revolver<TwentyTwo>()
      ThreeEighty::class -> Revolver<ThreeEighty>()
      FortyFive::class -> Revolver<FortyFive>()
      else -> throw IllegalArgumentException("Unsupported caliber: $revolverCaliber")
    }
    private val rifle = Rifle<RifleBullet>()

    fun <T : Bullet> reload(caliber: KClass<T>): MutableList<*> {
      val ammoList = when (caliber) {
        TwentyTwo::class -> ArrayList(ammoOfTwentyTwo).also { ammoOfTwentyTwo.clear() }
        ThreeEighty::class -> ArrayList(ammoOfThreeEighty).also { ammoOfThreeEighty.clear() }
        FortyFive::class -> ArrayList(ammoOfFortyFive).also { ammoOfFortyFive.clear() }
        RifleBullet::class -> ArrayList(ammoOfRifleBullet).also { ammoOfRifleBullet.clear() }
        else -> emptyList()
      }
      return ammoList.filter { !it.isShoot() && !it.isDamp() }.toMutableList()
    }

    fun getTheRevolver():Revolver<*>{
      return revolver
    }

    fun getTheRifle():Rifle<RifleBullet>{
      return rifle
    }
  }
}
