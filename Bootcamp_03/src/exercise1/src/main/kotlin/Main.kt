import kotlin.random.Random

fun main() {
  val revolverDrum = RevolverDrum<Int>()
  revolverDrum.addElement(54)
  revolverDrum.addElement(7)
  revolverDrum.addElement(2)
  revolverDrum.addElement(56)
  revolverDrum.addElement(4)
  revolverDrum.addElement(3)
  println("1. Adding elements")
  println("Structure: RevolverDrum<Int>")
  println("Objects: $revolverDrum")
  println("Pointer: ${revolverDrum.getPointer()}\n")
  revolverDrum.scroll()
  println("2. Scroll")
  println("Structure: RevolverDrum<Int>")
  println("Objects: $revolverDrum")
  println("Pointer: ${revolverDrum.getPointer()}\n")
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  println("3. Deletion")
  println("Structure: RevolverDrum<Int>")
  println("Objects: $revolverDrum")
  println("Pointer: ${revolverDrum.getPointer()}\n")
  val collection = mutableListOf<Int?>(4, 6, 3, 22, 77, 43, 76, 5)
  println("4. Supply collection")
  println("Before:")
  println("Supply collection: $collection\n")
  println("Structure: RevolverDrum<Int>")
  println("Objects: $revolverDrum")
  println("Pointer: ${revolverDrum.getPointer()}\n")
  revolverDrum.addAllElement(collection)
  println("After add operation performed:")
  println("Supply collection: $collection\n")
  println("Structure: RevolverDrum<Int>")
  println("Objects: $revolverDrum")
  println("Pointer: ${revolverDrum.getPointer()}\n")
}

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

class RevolverDrum<T>() {
  private var list: MutableList<T?> = MutableList(6) { null }
  private var index = Index(0)

  fun getPointer(): String {
    return "${list[index.toInt()]}"
  }

  private fun isFull():Boolean{
    if (list.isEmpty()||list.size < 5) return false
    for (i in 0 .. 5) {
      if (list[i] == null) return false
    }
    return true
  }

  fun addElement(element: T): Boolean {
    if (isFull())  return false
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
    index.nextElement()
    return element
  }

  fun removeAllElement(): MutableList<T?> {
    val removedElements = mutableListOf<T?>()
    list.filterNotNull().forEach { _ ->
      removedElements.add(removeElement())
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