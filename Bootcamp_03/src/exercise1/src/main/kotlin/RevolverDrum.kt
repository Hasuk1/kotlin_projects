class RevolverDrum<T>() {
  private var list: MutableList<T?> = MutableList(6) { null }
  private var index = Index(0)

  fun getPointer(): String {
    return "${list[index.toInt()]}"
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
    if (list[index.toInt()] == null) return null
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
    val otherRevolver = other as? RevolverDrum<*> ?: return false

    for (i in 0..5) {
      val thisElement = list[(index.toInt() + i) % 6]
      val otherElement = otherRevolver.list[(otherRevolver.index.toInt() + i) % 6]
      if (thisElement != otherElement) return false
    }

    return true
  }


  override fun toString(): String {
    val sublist = list.subList(index.toInt(), 6)
    sublist.addAll(list.subList(0, index.toInt()))
    return "$sublist"
  }

  override fun hashCode(): Int {
    var result = list.hashCode()
    result = 31 * result + index.hashCode()
    return result
  }
}