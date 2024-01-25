import kotlin.random.Random

class Index(private var index: Int) {
  fun <T> findNextFree(list: MutableList<T>): Index? {
    if (list[index] == null) return this
    var i = index
    var j = index
    while (i in 0..6 && j in 0..6) {
      if (i - 1 > 0 && list[--i] == null) {
        index = i
        return Index(index)
      }
      if (j + 1 < 6 && list[++j] == null) {
        index = j
        return Index(index)
      }
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