package bullets

class RifleBullet : Bullet() {
  override fun shoot() {
    shoot = true
    println("Ba-bang $this")
  }

  override fun load() {
    load = true
  }

  override fun extract() {
    load = false
  }

  override fun toString(): String {
    return "RifleBullet ${this.hashCode()}"
  }
}