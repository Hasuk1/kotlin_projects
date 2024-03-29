fun main() {
  fun printDrum(revolverDrum:RevolverDrum<Int>, structName: String = "RevolverDrum<Int>"){
    println("Structure: $structName")
    println("Objects: $revolverDrum")
    println("Pointer: ${revolverDrum.getPointer()}\n")
  }
  println("1. Adding elements")
  val revolverDrum = RevolverDrum<Int>()
  revolverDrum.addElement(54)
  revolverDrum.addElement(7)
  revolverDrum.addElement(2)
  revolverDrum.addElement(56)
  revolverDrum.addElement(4)
  revolverDrum.addElement(3)
  printDrum(revolverDrum)
  println("2. Scroll")
  revolverDrum.scroll()
  printDrum(revolverDrum)
  println("3. Deletion")
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  revolverDrum.deleteElement()
  printDrum(revolverDrum)
  println("4. Supply collection")
  val collection = mutableListOf<Int?>(4, 6, 3, 22, 77, 43, 76, 5)
  println("Before:")
  println("Supply collection: $collection\n")
  printDrum(revolverDrum)
  revolverDrum.addAllElement(collection)
  println("After add operation performed:")
  println("Supply collection: $collection\n")
  printDrum(revolverDrum)
  println("5. Extraction")
  val extractedList = revolverDrum.removeAllElement()
  println("The extracted list: $extractedList")
  println("size: ${extractedList.size}\n")
  printDrum(revolverDrum)
  println("size: ${revolverDrum.size()}\n")
  println("6. Supply collection 2")
  println("Before:")
  println("Supply collection: $collection\n")
  printDrum(revolverDrum)
  revolverDrum.addAllElement(collection)
  println("After add operation performed:")
  println("Supply collection: $collection\n")
  printDrum(revolverDrum)
  println("7. Equals")
  printDrum(revolverDrum)
  val revolverDrum2 = RevolverDrum<Int>()
  revolverDrum2.addElement(77)
  revolverDrum2.addElement(43)
  revolverDrum2.addElement(76)
  revolverDrum2.addElement(5)
  revolverDrum2.scroll()
  printDrum(revolverDrum2,"RevolverDrum2<Int>")
  println(if (revolverDrum == revolverDrum2) "equals" else "not equals")
}
