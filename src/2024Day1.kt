import java.io.File

class Year2024Day1 {

    private val filename = "data/2024/2024-Day1-1.txt"
    private val sampleFilename = "data/2024/2024-Day1-sample.txt"

    private val one = mutableListOf<Int>();
    private val two = mutableListOf<Int>();

    private fun readFile() {

        one.clear()
        two.clear()
        File(filename).forEachLine {
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

    private fun handleLine(line: String) {

        val parts = line.split("  ")
        one.add(parts[0].trim().toInt())
        two.add(parts[1].trim().toInt())
    }
}

fun main() {

    val challenge = Year2024Day1();

    println(challenge.part1());
    println(challenge.part2());
}
