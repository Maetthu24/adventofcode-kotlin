package year2022

import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var register = 1
        var cycle = 1
        return buildList {
            for (op in input) {
                when (op) {
                    "noop" -> cycle += 1
                    else -> {
                        cycle += 1
                        if ((cycle - 20) % 40 == 0) {
                            add(register * cycle)
                        }
                        cycle += 1
                        register += op.split(" ").last().toInt()
                    }
                }
                if ((cycle - 20) % 40 == 0) {
                    add(register * cycle)
                }
            }
        }.sum()
    }

    fun draw(image: List<Char>) {
        for (line in image.windowed(40, 40)) {
            println(line.joinToString(""))
        }
        println()
    }

    fun part2(input: List<String>) {
        var register = 1
        var cycle = 0
        val image = MutableList(240) { '.' }
        for (op in input) {
            if (abs(register - cycle % 40) <= 1) {
                image[cycle] = '#'
            }
            when (op) {
                "noop" -> cycle += 1
                else -> {
                    cycle += 1
                    if (abs(register - cycle % 40) <= 1) {
                        image[cycle] = '#'
                    }
                    cycle += 1
                    register += op.split(" ").last().toInt()
                }
            }
            if (abs(register - cycle % 40) <= 1) {
                image[cycle] = '#'
            }
        }
        draw(image)
    }

    val day = "10"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 13140
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(part1(input))

    // Test & run part 2
    println("Test image:")
    part2(testInput)
    println("\n---------------------------------\nActual image:")
    part2(input)
}