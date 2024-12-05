import java.io.File

class Year2024Day3 {

    val filename = "data/2024/2024-Day3-1.txt"
    val sampleFilename = "data/2024/2024-Day3-sample.txt"

    var line = ""

    fun part1(): String {

        readFileIntoSingleString()

        val regex = Regex("mul\\((\\d+,\\d+)\\)")

        val matches = regex.findAll(this.line)
        var total = 0
        for (match in matches) {
            total += calculate(match.value)
        }

        return total.toString()
    }

    private fun calculate(value: String): Int {

        val clean = value.replace("mul(", "").replace(")", "")
        val parts = clean.split(",")
        return parts[0].toInt() * parts[1].toInt()
    }

    fun part2(): String {

        readFileIntoSingleString()

        val startMap = buildStartMap()

        val calcMap = HashMap<Int, Int>()

        val multiplications = findMultiplications()
        for (multiplication in multiplications) {
            calcMap[multiplication.index] = multiplication.value
        }

        val maxMultiplicationIndex = multiplications.maxBy { it.index }.index

        var total = 0
        var include = true
        for (i in 0..maxMultiplicationIndex) {
            if (startMap.containsKey(i)) {
                include = startMap[i] == true
            }
            if (calcMap.containsKey(i)) {
                if (include) {
                    val value = calcMap[i] ?: 0
                    total += value
                }
            }
        }

        return total.toString()
    }

    private fun buildStartMap(): Map<Int, Boolean> {

        val dos = findOther("do()", true)
        val donts = findOther("don't()", false)

        val doIterator = dos.iterator()
        val dontsIterator = donts.iterator()

        val startMap = HashMap<Int, Boolean>()

        while (doIterator.hasNext()) {
            val other = doIterator.next();
            startMap[other.index] = other.start
        }
        while (dontsIterator.hasNext()) {
            val other = dontsIterator.next();
            startMap[other.index] = other.start
        }

        return startMap
    }

    private fun findOther(pattern: String, action: Boolean): List<Other> {

        val regex = Regex(pattern)

        val others = mutableListOf<Other>()

        val matches = regex.findAll(this.line)
        for (match in matches) {
            val other = Other(match.range.first, action)
            others.add(other)
        }

        return others
    }

    private fun findMultiplications(): List<Multiplication> {

        val regex = Regex("mul\\((\\d+,\\d+)\\)")

        val multiplications = mutableListOf<Multiplication>()

        val matches = regex.findAll(this.line)
        for (match in matches) {
            val value = calculate(match.value)
            val multiplication = Multiplication(match.range.first, value)
            multiplications.add(multiplication)
        }

        return multiplications
    }

    fun readFileIntoSingleString() {

        var wholeLine = ""

        File(filename).forEachLine { line ->
            wholeLine += line.replace("\n", "")
        }

        this.line = wholeLine
    }

    class Multiplication(val index: Int, val value: Int)

    class Other(val index: Int, val start: Boolean)
}

fun main() {

    val challenge = Year2024Day3();

    println(challenge.part1());
    println(challenge.part2());
}
