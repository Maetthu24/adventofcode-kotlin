package year2022

fun main() {

    fun parseInput(input: String) = input.split("\n\n").map { part ->
        part.lines().sumOf { it.toInt() }
    }

    fun part1(input: List<Int>): Int {
        return input.max()
    }

    fun part2(input: List<Int>): Int {
        return input.sortedDescending().take(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readText("Day01_test"))
    val testResult = part1(testInput)
    val testExpected = 24000
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }

    val input = parseInput(readText("Day01"))
    println(part1(input))
    println(part2(input))
}
