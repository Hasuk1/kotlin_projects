import kotlin.math.*

fun main() {
    val x1 = readCoordinate("Input x1:")
    val y1 = readCoordinate("Input y1:")
    val r1 = readRadius("Input r1:")
    val x2 = readCoordinate("Input x2:")
    val y2 = readCoordinate("Input y2:")
    val r2 = readRadius("Input r2:")
    val distance = sqrt((x2 - x1).pow(2) + (y2 - y1).pow(2))
    when {
        distance < r1 - r2 -> println("Result: circle 2 is inside circle 1")
        distance < r2 - r1 -> println("Result: circle 1 is inside circle 2")
        distance < r1 + r2 -> println("Result: the circles intersect")
        distance == r1 + r2 -> println("Result: the circles touch each other")
        distance > r1 + r2 -> println("Result: the circles do not intersect")
        else -> println("Result: unexpected case")
    }
}

fun readCoordinate(prompt: String): Double {
    while (true) {
        try {
            println(prompt)
            return readLine()?.toDouble() ?: throw NumberFormatException()
        } catch (e: NumberFormatException) {
            println("Failed to parse the number. Please try again.")
        }
    }
}

fun readRadius(prompt: String): Double {
    while (true) {
        try {
            println(prompt)
            val radius = readLine()?.toDouble() ?: throw NumberFormatException()
            if (radius < 0) throw IllegalArgumentException("Radius must be non-negative.")
            return radius
        } catch (e: NumberFormatException) {
            println("Failed to parse the number. Please try again.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}
