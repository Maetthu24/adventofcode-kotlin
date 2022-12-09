package year2022

import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        return buildSet {
            var tailX = 0
            var tailY = 0
            var headX = 0
            var headY = 0
            add(Pair(tailX, tailY))
            for ((dir, n) in input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }) {
                for (i in 1 .. n) {
                    headY = when (dir) {
                        "U" -> headY + 1
                        "D" -> headY - 1
                        else -> headY
                    }
                    headX = when (dir) {
                        "R" -> headX + 1
                        "L" -> headX - 1
                        else -> headX
                    }
                    if (abs(headX - tailX) <= 1 && abs(headY - tailY) <= 1)
                        continue
                    if (abs(headX - tailX) == 2) {
                        tailX = if (headX > tailX) tailX + 1 else tailX - 1
                        when (abs(tailY - headY)) {
                            1 -> tailY = if (headY > tailY) tailY + 1 else tailY - 1
                            2 -> error("Should not happen...")
                        }
                    }
                    if (abs(headY - tailY) == 2) {
                        tailY = if (headY > tailY) tailY + 1 else tailY - 1
                        when (abs(tailX - headX)) {
                            1 -> tailX = if (headX > tailX) tailX + 1 else tailX - 1
                            2 -> error("Should not happen...")
                        }
                    }
                    add(Pair(tailX, tailY))
                }
            }
        }.size
    }

    fun part2(input: List<String>): Int {
        return buildSet {
            val positions = (0..9).map { Pair(0, 0) }.toMutableList()
            add(positions.last())
            for ((dir, n) in input.map { Pair(it.split(" ")[0], it.split(" ")[1].toInt()) }) {
                for (i in 1 .. n) {
                    loop@ for (p in 0..8) {
                        var (headX, headY) = positions[p]
                        var (tailX, tailY) = positions[p+1]
                        if (p == 0) {
                            headY = when (dir) {
                                "U" -> headY + 1
                                "D" -> headY - 1
                                else -> headY
                            }
                            headX = when (dir) {
                                "R" -> headX + 1
                                "L" -> headX - 1
                                else -> headX
                            }
                        }
                        if (abs(headX - tailX) <= 1 && abs(headY - tailY) <= 1) {
                            positions[p] = Pair(headX, headY)
                            positions[p+1] = Pair(tailX, tailY)
                            if (p == 8) {
                                add(Pair(tailX, tailY))
                            }
                            continue@loop
                        }
                        if (abs(headX - tailX) == 2) {
                            tailX = if (headX > tailX) tailX + 1 else tailX - 1
                            when (abs(tailY - headY)) {
                                1 -> tailY = if (headY > tailY) tailY + 1 else tailY - 1
                                2 -> tailY = if (headY > tailY) tailY + 1 else tailY - 1
                                3 -> error("Should not happen...")
                            }
                        }
                        if (abs(headY - tailY) == 2) {
                            tailY = if (headY > tailY) tailY + 1 else tailY - 1
                            when (abs(tailX - headX)) {
                                1 -> tailX = if (headX > tailX) tailX + 1 else tailX - 1
                                2 -> tailX = if (headX > tailX) tailX + 1 else tailX - 1
                                3 -> error("Should not happen...")
                            }
                        }
                        if (p == 8) {
                            add(Pair(tailX, tailY))
                        }
                        positions[p] = Pair(headX, headY)
                        positions[p+1] = Pair(tailX, tailY)
                    }
                }
            }
        }.size
    }

    val day = "09"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val testInput2 = readInput("Day${day}_test2")
    val input = readInput("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 13
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(part1(input))

    // Test & run part 2
    val testResult2 = part2(testInput)
    val testExpected2 = 1
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    val testResult3 = part2(testInput2)
    val testExpected3 = 36
    check(testResult3 == testExpected3) { "testResult2 should be $testExpected3, but is $testResult3" }
    println(part2(input))
}