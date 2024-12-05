import java.io.File

const val FILENAME = "data/2024/2024-Day1-1.txt"
const val SAMPLE_FILENAME = "data/2024/2024-Day1-sample.txt"

val one = mutableListOf<Int>();
val two = mutableListOf<Int>();

fun main() {

    println(part1());
    println(part2());
}

fun readFile() {

    one.clear()
    two.clear()
    File(FILENAME).forEachLine {
            line -> handleLine(line)
    }

}

fun part1(): String {

    readFile()
    one.sort()
    two.sort()

    var total = 0
    for (i in 0..one.size-1) {
        val diff = Math.abs(one.get(i) - two.get(i))
        total += diff
    }

    return total.toString()
}

fun part2(): String {

    readFile()

    var total = 0
    for (i in 0..one.size-1) {
        val first = one.get(i)
        val second = two.count {it == first}
        total += (first * second)
    }

    return total.toString()

}

fun handleLine(line: String) {

    val parts = line.split("  ")
    one.add(parts[0].trim().toInt())
    two.add(parts[1].trim().toInt())
}
