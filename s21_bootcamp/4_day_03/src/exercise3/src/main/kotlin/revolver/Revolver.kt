package revolver

import bullets.Bullet

class Revolver<T : Bullet> {
  var drum = RevolverDrum<T?>()

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

  fun size(): Int {
    return drum.size()
  }

  fun getCurrentCartridge(): T? {
    return drum.getPointer()
  }

  fun scroll() {
    drum.scroll()
  }

  fun shoot(): T? {
    val bullet = drum.getPointer()
    if (bullet != null && !bullet.isDamp() && bullet.isLoad() && !bullet.isShoot()) {
      bullet.shoot()
    } else when {
      bullet == null -> println("Click.")
      bullet.isDamp() -> println("Click. $bullet is damp")
      !bullet.isLoad() -> println("Click. $bullet isn't in the chamber")
      bullet.isShoot() -> println("Click. $bullet is already shot")
    }
    return drum.removeElement()
  }

  fun extract():T?{
    return drum.removeElement()
  }

  fun quickDraw(): MutableList<T?> {
    val firedBullets = mutableListOf<T?>()
    for (i in 0..5) {
      firedBullets.add(shoot())
    }
    return firedBullets
  }

  override fun toString(): String {
    return "$drum"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is Revolver<*>) return false
    return drum == other.drum
  }

  override fun hashCode(): Int {
    return drum.hashCode()
  }
}