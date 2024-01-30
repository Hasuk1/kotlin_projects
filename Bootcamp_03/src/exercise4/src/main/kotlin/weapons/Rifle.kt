package weapons

import bullets.Bullet

class Rifle <T : Bullet>{
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
}