fun main() {
    Transformations()
}

class Transformations {

    init {
        println("editImmutableList")
        println(editImmutableList())

        editMutableList()
        filterList()
        filterAndMapList()
        flatMapList()
        filterNotNull()
    }

    fun editImmutableList(): List<Int> {
        val immutableList = listOf(1, 2, 3, 4, 5)
        val resultList = ArrayList<Int>()

        resultList.addAll(immutableList)
        resultList.add(6)

        return resultList
    }

    fun editMutableList() {
        println("editMutableList")

        val mutableList = mutableListOf(1, 2, 3, 4, 5)

        println(mutableList)

        mutableList.add(6)
        mutableList.removeAt(0)
        mutableList.remove(3)

        println(mutableList)
    }

    fun filterList() {
        println("filterList")

        val integers = listOf(1, 4, 6, -3, 4, -6, 3)
        println(integers)

        val naturals = integers.filter { it >= 0 }
        println(naturals)
    }

    fun filterAndMapList() {
        println("filterAndMapList")

        val strings = listOf("One", "Two", "Three", "Four", "Five")
        println(strings)

        strings
            .filter { it.contains('o') || it.contains('O') }
            .map { "$it is a number" }
            .also { println(it) } // Standard Kotlin high order function for working in a context of an object
    }

    fun flatMapList() {
        println("flatMapList")

        val listOfLists = listOf(ObjectWithList(), ObjectWithList(), ObjectWithList())
        println(listOfLists)

        listOfLists
            .flatMap { it.list }
            .let { println(it) } // Another standard Kotlin high order function for working in a context of an object
    }

    fun filterNotNull() {
        println("filterNotNull")

        val listWithNulls = listOf(1, 2, 3, null, 4, null, 5, 6, null)
        println(listWithNulls)

        listWithNulls
            .filterNotNull()
            .apply { println(this) } // Another standard Kotlin high order function for working in a context of an object
    }
}

data class ObjectWithList(
    val list: List<Int> = listOf(0, 1) // Default value of a variable
)