fun main() {
  println("1.")
  val str1 = "text"
  val str2 = "text"
  val int = 1
  println("String, Int - ${isObjCompare(str1, int)}")
  println("String, String - ${isObjCompare(str1, str2)}\n")
  println("2.0")
  val value = First("some value")
  val mapper = TypeMapper()
  println("from: $value\nto: ${mapper.convert(value)}\n")
  println("2.1")
  val valueList = mutableListOf(First("value 1"), First("value 2"), First("value 3"))
  println("from: $valueList\nto: ${mapper.convertList(valueList)}\n")
  println("3.")
  val animal = Dog()
  println("Is a dog an dog? ${animal.isType(Dog())}")
  println("Is a dog an cat? ${animal.isType(Cat())}")
  println("Is a dog an animal? ${animal.isType(Animal())}\n")
  println("4.")
  val description = "The delegate will replace all spaces with underscores"
  val editor = Editor(StringCorrector())
  println("Before: $description")
  println("After: ${editor.replaceSpaceToUnderscores(description)}\n")
  println("5.")
  val log = Logger()
  log.str = "Hello kotlin"
  log.str
  println("\n6.")
  println("Addition result: ${operation(100, 54.4, MathOperation.ADD)}")
  println("Subtraction result: ${operation(85, 2.5, MathOperation.SUB)}")
  println("Multiplication result: ${operation(28.4, 4, MathOperation.MUL)}")
  println("Division result: ${operation(256.8, 8, MathOperation.DIV)}")
}

data class First(val name: String)
data class Second(val name: String)

class TypeMapper : Mapper<First, Second>() {
  override fun convert(value: First): Second {
    return Second(value.name)
  }

  override fun convertList(list: MutableList<First>): MutableList<Second> {
    return list.mapTo(mutableListOf()) { convert(it) }
  }
}

open class Animal
class Dog : Animal()
class Cat : Animal()

class Editor(d: StringCorrector) : CorrectorDelegate by d

class Logger {
  var str: String by LoggingDelegate()
}