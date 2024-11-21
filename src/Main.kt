import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    println(
        "Затраченное время: ${
            measureTimeMillis {
                val intList = getRandomList(10)
                val charList = getRandomList("10")
                val firstSize = async { unpack(intList) }
                val secondSize = async { unpack(charList) }
                println("Общий размер: " + (firstSize.await() + secondSize.await()))
                println(concatenate(intList, charList))
            }
        } мс"
    )
}

fun getRandomList(size: String) = List(size.toInt()) {
    (Char.MIN_VALUE..Char.MAX_VALUE).random()
}

fun getRandomList(size: Int) = List(size) {
    Random.nextInt()
}

suspend fun <T> unpack(list: List<T>): Int {
    var count = 0
    list.forEach {
        delay(1000L)
        println(it)
        count++
    }
    return count
}

fun <T> concatenate(vararg list: List<T>): Pair<Int, MutableList<T>> {
    val newList = mutableListOf<T>()
    list.forEach { collection ->
        collection.forEach { newList.add(it) }
    }
    return Pair(newList.size, newList)
}