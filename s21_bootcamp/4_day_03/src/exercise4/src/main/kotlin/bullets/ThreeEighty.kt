package bullets

class ThreeEighty : PistolBullet() {
  override fun shoot() {
    shoot = true
    println("Bang $this")
  }

  override fun load() {
    load = true
  }

  override fun extract() {
    load = false
  }

  override fun toString(): String {
    return "ThreeEighty ${this.hashCode()}"
  }
}