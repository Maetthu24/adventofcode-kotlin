package year2022

fun main() {
    fun part1(input: List<String>): Int {
        val letters = "abcdefghijklmnopqrstuvwxyz"
        var start = Pair(0, 0)
        var destination = Pair(0, 0)
        val visited = mutableListOf<Pair<Int, Int>>()
        val grid = input.withIndex().map { (x, line) ->
            line.withIndex().map { (y, c) ->
                when (c) {
                    'S' -> {
                        start = Pair(x, y)
                        0
                    }
                    'E' -> {
                        destination = Pair(x, y)
                        25
                    }
                    else -> letters.indexOf(c)
                }
            }
        }

        val gridX = grid.size
        val gridY = grid[0].size

        fun Pair<Int, Int>.notVisitedNeighbors(): List<Pair<Int, Int>> {
            return buildList {
                if (first > 0)
                    add(Pair(first - 1, second))
                if (second > 0)
                    add(Pair(first, second - 1))
                if (first < gridX - 1)
                    add(Pair(first + 1, second))
                if (second < gridY - 1)
                    add(Pair(first, second + 1))
            }.filter { !visited.contains(it) && grid[this.first][this.second] - 1 <= grid[it.first][it.second] }
        }

        val distances = mutableMapOf<Pair<Int, Int>, Int>()
        val queue = mutableListOf<Pair<Int, Int>>()
        queue.add(destination)
        visited.add(destination)
        distances[destination] = 0

        while (queue.isNotEmpty()) {
            val new = queue.removeFirst()
            if (new == start)
                break

            for (n in new.notVisitedNeighbors()) {
                visited.add(n)
                distances[n] = distances[new]!! + 1
                queue.add(n)
            }
        }

        return distances[start]!!
    }

    fun part2(input: List<String>): Int {
        val letters = "abcdefghijklmnopqrstuvwxyz"
        var start = Pair(0, 0)
        var destination = Pair(0, 0)
        val visited = mutableListOf<Pair<Int, Int>>()
        val grid = input.withIndex().map { (x, line) ->
            line.withIndex().map { (y, c) ->
                when (c) {
                    'S' -> {
                        start = Pair(x, y)
                        0
                    }
                    'E' -> {
                        destination = Pair(x, y)
                        25
                    }
                    else -> letters.indexOf(c)
                }
            }
        }

        val gridX = grid.size
        val gridY = grid[0].size

        fun Pair<Int, Int>.notVisitedNeighbors(): List<Pair<Int, Int>> {
            return buildList {
                if (first > 0)
                    add(Pair(first - 1, second))
                if (second > 0)
                    add(Pair(first, second - 1))
                if (first < gridX - 1)
                    add(Pair(first + 1, second))
                if (second < gridY - 1)
                    add(Pair(first, second + 1))
            }.filter { !visited.contains(it) && grid[this.first][this.second] - 1 <= grid[it.first][it.second] }
        }

        val distances = mutableMapOf<Pair<Int, Int>, Int>()
        val queue = mutableListOf<Pair<Int, Int>>()
        queue.add(destination)
        visited.add(destination)
        distances[destination] = 0

        var found = Pair(0, 0)

        while (queue.isNotEmpty()) {
            val new = queue.removeFirst()
            if (grid[new.first][new.second] == 0) {
                found = new
                break
            }

            for (n in new.notVisitedNeighbors()) {
                visited.add(n)
                distances[n] = distances[new]!! + 1
                queue.add(n)
            }
        }

        return distances[found]!!
    }

    val day = "12"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 31
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(part1(input))

    // Test & run part 2
    val testResult2 = part2(testInput)
    val testExpected2 = 29
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    println(part2(input))
}