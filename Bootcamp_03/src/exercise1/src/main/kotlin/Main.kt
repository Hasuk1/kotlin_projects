fun main(){
  val kek = RevolverDrum<Int>()
  val col = mutableListOf(3, 54, 7, 2, 56, 4)
  kek.list.addAll(0, col)
  println(kek)
}

class RevolverDrum<T>() {
  var list: MutableList<T?> = MutableList(6) { null }
  //  private var list: MutableList<Int?> = mutableListOf(3, 54, 7, 2, 56, 4)
  private var index = 0

  override fun toString(): String {
    val newList: MutableList<T?> = mutableListOf()

    // Копируем элементы от индекса index до конца списка
    for (i in index until 6) {
      newList.add(list[i])
    }

    // Копируем элементы от начала списка до индекса index
    for (i in 0 until index) {
      newList.add(list[i])
    }
//    newList.subList(6, newList.size).clear()
    return "$newList"
  }
}