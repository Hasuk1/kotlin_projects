import kotlin.random.Random

class RevolverDrum<T>() {
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
  private var list: MutableList<T?> = MutableList(6) { null }
  private var index = Index(0)

  fun getPointer(): String {
    return "${list[index.toInt()]}"
  }

  fun getIndex(): Int {
    return index.toInt()
  }

  fun isFull(): Boolean {
    if (list.isEmpty() || list.size < 5) return false
    for (i in 0..5) {
      if (list[i] == null) return false
    }
    return true
  }

  fun isEmpty(): Boolean {
    if (list.isEmpty()) return true
    for (i in 0..5) {
      if (list[i] != null) return false
    }
    return true
  }

  fun addElement(element: T?): Boolean {
    if (isFull()) return false
    index = index.findNextFree(list) ?: return false
    list[index.toInt()] = element
    return true
  }

  fun addAllElement(elementList: MutableList<T?>): Boolean {
    if (elementList.isEmpty()) return false
    val iterator = elementList.iterator()
    while (iterator.hasNext() && !isFull()) {
      val element = iterator.next()
      if (element != null) {
        index.nextElement()
        addElement(element)
        iterator.remove()
      }
    }
    return true
  }

  fun deleteElement(): Boolean {
    if (list.isEmpty()) return false
    if (list[index.toInt()] == null) return false
    list[index.toInt()] = null
    index.nextElement()
    return true
  }

  fun removeElement(): T? {
    if (list.isEmpty()) return null
    val element = list[index.toInt()]
    list[index.toInt()] = null
    index.nextElement()
    return element
  }

  fun removeAllElement(): MutableList<T?> {
    val removedElements = mutableListOf<T?>()
    for (i in 0..5) {
      if (list[index.toInt()] != null) removedElements.add(removeElement())
    }
    return removedElements
  }

  fun scroll() {
    index.randomElement()
  }

  fun size(): Int {
    val sublist = list.subList(0, 6).filterNotNull()
    return sublist.size
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is RevolverDrum<*>) return false
    val otherList = other.toString()
    for (i in 1..6) {
      val list = toString()
      index.nextElement()
      if (otherList == list) return true
    }
    return false
  }

  override fun toString(): String {
    val output: MutableList<T?> = MutableList(6) { null }
    for (i in 0..5) {
      output.add(i, list[index.toInt()])
      index.nextElement()
    }
    return "${output.subList(0, 6)}"
  }

  override fun hashCode(): Int {
    var result = list.hashCode()
    result = 31 * result + index.hashCode()
    return result
  }
}