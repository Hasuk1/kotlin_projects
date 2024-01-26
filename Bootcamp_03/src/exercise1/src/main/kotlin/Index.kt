import kotlin.random.Random

class Index(private var index: Int) {
  fun <T> findNextFree(list: MutableList<T>): Index? {
    for (i in 0..5) {
      if (list[index] == null) return this
      nextElement()
    }
    return null
  }

  fun nextElement() {
    if (index < 5) index++ else index = 0
  }

  fun randomElement() {
    index = Random.nextInt(6)
  }

  fun toInt(): Int {
    return index
  }
}