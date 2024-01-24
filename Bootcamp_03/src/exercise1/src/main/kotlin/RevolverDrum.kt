class RevolverDrum<T>() {
  private var list: MutableList<T?> = MutableList(6) { null }
  private var index = 0
}

//toString 1. print from index to end 2. print from begin until index