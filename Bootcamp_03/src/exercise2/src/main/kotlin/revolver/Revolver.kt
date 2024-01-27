package revolver

import bullets.Bullet

class Revolver<T : Bullet> {
  private val drum = RevolverDrum<T?>()

  fun load(bullet: T): Boolean {
    if (bullet.isLoad()) {
      println("Bullet $bullet is already in the chamber. Ignoring.")
      return false
    }
    return if (drum.addElement(bullet)) {
      bullet.load()
      true
    } else {
      false
    }
  }

  fun loadMany(bulletBunch: MutableList<T?>): Boolean {
    if (bulletBunch.isEmpty()) return false
    bulletBunch.filterNotNull().forEach { bullet ->
      if (bullet.isLoad()) {
        println("Bullet $bullet is already in the chamber. Ignoring.")
      } else if (!drum.isFull() && !bullet.isLoad()) {
        if (drum.addElement(bullet)) bullet.load()
        bulletBunch.remove(bullet)
      }
    }
    return true
  }

  override fun toString(): String {
    return "$drum"
  }
}