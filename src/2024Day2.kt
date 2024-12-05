import java.io.File

class Year2024Day2 {

    private val filename = "data/2024/2024-Day2-1.txt"
    private val sampleFilename = "data/2024/2024-Day2-sample.txt"

    fun part1(): String {

        var validCount = 0
        val levelLists = readFile()

        for (levels in levelLists) {
            val valid = levelIsValid(levels)
            if (valid) validCount++;
        }

        return validCount.toString()
    }

    fun part2(): String {

        var validCount = 0
        val levelLists = readFile()

        for (levels in levelLists) {
            val valid = canLevelBeMadeValid(levels)
            if (valid) validCount++;
        }

        return validCount.toString()
    }

    private fun canLevelBeMadeValid(levels: IntArray): Boolean {

        if (levelIsValid(levels)) {
            return true
        }

        for (i in 0..levels.size - 1) {
            var shortLevels = mutableListOf<Int>()
            for (j in 0..levels.size - 1) {
                if (i != j) {
                    shortLevels.add(levels.get(j))
                }
            }
            if (levelIsValid(shortLevels.toIntArray())) {
                return true
            }
        }
        return false
    }

    private fun readFile(): List<IntArray> {

        val lines = mutableListOf<IntArray>()
        File(filename).forEachLine { line ->
            lines.add(mapLevels(line))
        }

        return lines
    }

    private fun levelIsValid(levels: IntArray): Boolean {

        var valid = true
        var delta = 0

        for (i in 0..levels.size - 2) {
            val one = levels.get(i)
            val two = levels.get(i + 1)
            val diff = Math.abs(two - one)
            if (diff < 1 || diff > 3) {
                valid = false
            }
            if (delta == 0) {
                delta = figure_delta(one, two)
            } else {
                if (delta != figure_delta(one, two)) {
                    valid = false
                }
            }
        }

        return valid
    }

    private fun figure_delta(one: Int, two: Int): Int {

        return if (one > two) 1 else -1
    }

    private fun mapLevels(line: String): IntArray {

        val parts = line.split(" ")
        return parts.map { it.toInt() }.toIntArray()
    }
}


fun main() {

    val challenge = Year2024Day2();

    println(challenge.part1());
    println(challenge.part2());
}

