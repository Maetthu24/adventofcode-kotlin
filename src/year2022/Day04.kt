package year2022

fun main() {
    fun part1(input: List<String>): Int {
        return input.count {
            val parts = it.split(",")
            val range1 =
                parts.first().split("-").first().toInt()..parts.first().split("-").last().toInt()
            val range2 =
                parts.last().split("-").first().toInt()..parts.last().split("-").last().toInt()
            (range1.first >= range2.first && range1.last <= range2.last) || (range2.first >= range1.first && range2.last <= range1.last)
        }
    }

    fun part2(input: List<String>): Int {
        return input.count {
            val parts = it.split(",")
            val range1 =
                parts.first().split("-").first().toInt()..parts.first().split("-").last().toInt()
            val range2 =
                parts.last().split("-").first().toInt()..parts.last().split("-").last().toInt()
            range1.first <= range2.last && range2.first <= range1.last
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val testResult = part1(testInput)
    val testExpected = 2
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    val testResult2 = part2(testInput)
    val testExpected2 = 4
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}