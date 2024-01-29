import bullets.*

class Player {
  private val ammoOfTwentyTwo=MutableList(10) { TwentyTwo() }
  private val ammoOfThreeEighty=MutableList(7) { ThreeEighty() }
  private val ammoOfFortyFive=MutableList(44) { FortyFive() }
  private val ammoOfRifleBullet=MutableList(12) { RifleBullet() }
}