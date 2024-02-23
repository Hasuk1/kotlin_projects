package bullets

class ThreeEighty : Bullet() {
  override fun shoot() {
    shoot = true
    println("Bang $this")
  }

  override fun load() {
    load = true
  }

  override fun toString(): String {
    return "ThreeEighty ${this.hashCode()}"
  }
}