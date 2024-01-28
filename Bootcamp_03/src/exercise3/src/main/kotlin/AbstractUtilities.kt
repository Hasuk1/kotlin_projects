import kotlin.reflect.KProperty

fun isObjCompare(firstObj: Any?, secondObj: Any?): Boolean {
  return firstObj?.javaClass == secondObj?.javaClass
}

abstract class Mapper<A, B> {
  abstract fun convert(value: A): B
  abstract fun convertList(list: MutableList<A>): MutableList<B>
}

fun <T> Any.isType(obj: T?): Boolean {
  return obj?.let { it::class.java.isInstance(this) } ?: false
}

interface CorrectorDelegate {
  fun replaceSpaceToUnderscores(str: String): String
}

class StringCorrector() : CorrectorDelegate {
  override fun replaceSpaceToUnderscores(str: String): String {
    return str.replace(' ', '_')
  }
}

class LoggingDelegate {
  private var str: String = ""
  operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
    println("Getting value of property ${property.name}: $str")
    return str
  }

  operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
    println("Setting value of property ${property.name} to: $newValue")
    str = newValue
  }
}

enum class MathOperation { ADD, SUB, MUL, DIV }

fun <T : Number> operation(firstOperand: T, secondOperand: T, operation: MathOperation): T {
  @Suppress("UNCHECKED_CAST")
  return when (operation) {
    MathOperation.ADD -> (firstOperand.toDouble() + secondOperand.toDouble()).coerceIn(
      Double.MIN_VALUE,
      Double.MAX_VALUE
    )

    MathOperation.SUB -> (firstOperand.toDouble() - secondOperand.toDouble()).coerceIn(
      Double.MIN_VALUE,
      Double.MAX_VALUE
    )

    MathOperation.MUL -> (firstOperand.toDouble() * secondOperand.toDouble()).coerceIn(
      Double.MIN_VALUE,
      Double.MAX_VALUE
    )

    MathOperation.DIV -> (firstOperand.toDouble() / secondOperand.toDouble()).coerceIn(
      Double.MIN_VALUE,
      Double.MAX_VALUE
    )
  } as T
}