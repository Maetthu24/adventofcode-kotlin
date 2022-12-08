package year2022

fun main() {
    fun isVisible(x: Int, y: Int, list: List<List<Int>>): Boolean {
        if (x == 0 || x == list[y].size - 1 || y == 0 || y == list.size - 1)
            return true
        val tree = list[y][x]
        if ((0 until x).toList().all { list[y][it] < tree })
            return true
        if ((x + 1 until list[y].size).toList().all { list[y][it] < tree })
            return true
        if ((0 until y).toList().all { list[it][x] < tree })
            return true
        if ((y + 1 until list.size).toList().all { list[it][x] < tree })
            return true
        return false
    }

    fun part1(input: List<String>): Int {
        val list = input.map { it.map { c -> c.digitToInt() } }
        return list.indices.sumOf { y ->
            list[y].indices.count { x ->
                isVisible(x, y, list)
            }
        }
    }

    fun score(x: Int, y: Int, list: List<List<Int>>): Int {
        var x1 = 0
        val tree = list[y][x]
        if (x > 0) {
            for (i in x-1 downTo 0) {
                x1++
                if (list[y][i] >= tree) {
                    break
                }
            }
        }
        var x2 = 0
        if (x < list[y].size - 1) {
            for (i in x+1 until list[y].size) {
                x2++
                if (list[y][i] >= tree) {
                    break
                }
            }
        }
        var y1 = 0
        if (y > 0) {
            for (i in y-1 downTo 0) {
                y1++
                if (list[i][x] >= tree) {
                    break
                }
            }
        }

        var y2 = 0
        if (y < list.size - 1) {
            for (i in y+1 until list.size) {
                y2++
                if (list[i][x] >= tree) {
                    break
                }
            }
        }
        return x1 * x2 * y1 * y2
    }

    fun part2(input: List<String>): Int {
        val list = input.map { it.map { c -> c.digitToInt() } }
        return list.indices.maxOf { y ->
            list[y].indices.maxOf { x ->
                score(x, y, list)
            }
        }
    }

    val day = "08"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 21
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(part1(input))

    // Test & run part 2
    val testResult2 = part2(testInput)
    val testExpected2 = 8
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    println(part2(input))
}