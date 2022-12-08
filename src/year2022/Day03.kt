package year2022

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            val p1 = line.take(line.length / 2)
            val p2 = line.takeLast(line.length / 2)
            for (c in p1) {
                if (p2.contains(c)) {
                    sum += if (c.isLowerCase()) {
                        c.code - 96
                    } else {
                        c.code - 38
                    }
                    break
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (lines in input.windowed(3, step = 3)) {
            for (c in lines[0]) {
                if (lines[1].contains(c) && lines[2].contains(c)) {
                    sum += if (c.isLowerCase()) {
                        c.code - 96
                    } else {
                        c.code - 38
                    }
                    break
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testResult = part1(testInput)
    val testExpected = 157
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    val testResult2 = part2(testInput)
    val testExpected2 = 70
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}