package year2022

import kotlin.collections.ArrayDeque

fun main() {
    fun part1(input: List<String>, stacks: MutableList<ArrayDeque<Char>>): String {
        for (line in input.joinToString(separator = "\n").split("\n\n").last().split("\n")) {
            val parts = line.split(" ")
            val count = parts[1].toInt()
            val from = parts[3].toInt() - 1
            val to = parts[5].toInt() - 1
            for (i in 0 until count) {
                stacks[to].addLast(stacks[from].removeLast())
            }
        }
        return stacks.map { it.last() }.joinToString("")
    }

    fun part2(input: List<String>, stacks: MutableList<ArrayDeque<Char>>): String {
        for (line in input.joinToString(separator = "\n").split("\n\n").last().split("\n")) {
            val parts = line.split(" ")
            val count = parts[1].toInt()
            val from = parts[3].toInt() - 1
            val to = parts[5].toInt() - 1
            val moved = mutableListOf<Char>()
            for (i in 0 until count) {
                moved.add(stacks[from].removeLast())
            }
            for (m in moved.reversed()) {
                stacks[to].addLast(m)
            }
        }
        return stacks.map { it.last() }.joinToString("")
    }

    val day = "05"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    // Test & run part 1
    var testDeque = mutableListOf<ArrayDeque<Char>>()
    testDeque.add(ArrayDeque(listOf('Z', 'N')))
    testDeque.add(ArrayDeque(listOf('M', 'C', 'D')))
    testDeque.add(ArrayDeque(listOf('P')))
    val testResult = part1(testInput, testDeque)
    val testExpected = "CMZ"
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }

    var deque = mutableListOf<ArrayDeque<Char>>()
    deque.add(ArrayDeque(listOf('B', 'G', 'S', 'C')))
    deque.add(ArrayDeque(listOf('T', 'M', 'W', 'H', 'J', 'N', 'V', 'G')))
    deque.add(ArrayDeque(listOf('S', 'Q', 'M')))
    deque.add(ArrayDeque(listOf('B', 'S', 'L', 'T', 'W', 'N', 'M')))
    deque.add(ArrayDeque(listOf('J', 'Z', 'F', 'T', 'V', 'G', 'W', 'P')))
    deque.add(ArrayDeque(listOf('C', 'T', 'B', 'G', 'Q', 'H', 'S')))
    deque.add(ArrayDeque(listOf('T', 'J', 'P', 'B', 'W')))
    deque.add(ArrayDeque(listOf('G', 'D', 'C', 'Z', 'F', 'T', 'Q', 'M')))
    deque.add(ArrayDeque(listOf('N', 'S', 'H', 'B', 'P', 'F')))
    println(part1(input, deque))

    // Test & run part 2
    testDeque = mutableListOf()
    testDeque.add(ArrayDeque(listOf('Z', 'N')))
    testDeque.add(ArrayDeque(listOf('M', 'C', 'D')))
    testDeque.add(ArrayDeque(listOf('P')))

    deque = mutableListOf()
    deque.add(ArrayDeque(listOf('B', 'G', 'S', 'C')))
    deque.add(ArrayDeque(listOf('T', 'M', 'W', 'H', 'J', 'N', 'V', 'G')))
    deque.add(ArrayDeque(listOf('S', 'Q', 'M')))
    deque.add(ArrayDeque(listOf('B', 'S', 'L', 'T', 'W', 'N', 'M')))
    deque.add(ArrayDeque(listOf('J', 'Z', 'F', 'T', 'V', 'G', 'W', 'P')))
    deque.add(ArrayDeque(listOf('C', 'T', 'B', 'G', 'Q', 'H', 'S')))
    deque.add(ArrayDeque(listOf('T', 'J', 'P', 'B', 'W')))
    deque.add(ArrayDeque(listOf('G', 'D', 'C', 'Z', 'F', 'T', 'Q', 'M')))
    deque.add(ArrayDeque(listOf('N', 'S', 'H', 'B', 'P', 'F')))
    val testResult2 = part2(testInput, testDeque)
    val testExpected2 = "MCD"
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    println(part2(input, deque))
}