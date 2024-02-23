package bullets

import kotlin.random.Random

abstract class Bullet {
  private var damp = Random.nextBoolean()
  protected var shoot = false
  protected var load = false

  abstract fun shoot()

  abstract fun load()

  fun isDamp(): Boolean {
    return damp
  }

  fun isShoot(): Boolean {
    return shoot
  }

  fun isLoad(): Boolean {
    return load
  }
}