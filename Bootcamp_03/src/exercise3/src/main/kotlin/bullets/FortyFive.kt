package bullets

class FortyFive : Bullet() {
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
    return "FortyFive ${this.hashCode()}"
  }
}