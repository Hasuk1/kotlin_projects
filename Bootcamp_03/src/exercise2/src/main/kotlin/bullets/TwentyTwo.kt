package bullets

class TwentyTwo : Bullet() {
  override fun shoot() {
    shoot = true
    println("Bang $this")
  }

  override fun load() {
    load = true
  }

  override fun toString(): String {
    return "TwentyTwo ${this.hashCode()}"
  }
}