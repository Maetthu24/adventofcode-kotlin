fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            var s = 0
            when (it.last()) {
                'X' -> s += 1
                'Y' -> s += 2
                'Z' -> s += 3
            }
            when (it) {
                "A X", "B Y", "C Z" -> s += 3
                "A Y", "B Z", "C X" -> s += 6
            }
            s
        }
    }

    fun Char.draw(): Char {
        when(this) {
            'A' -> return 'X'
            'B' -> return 'Y'
            'C' -> return 'Z'
        }
        return '.'
    }

    fun Char.win(): Char {
        when(this) {
            'A' -> return 'Y'
            'B' -> return 'Z'
            'C' -> return 'X'
        }
        return '.'
    }

    fun Char.lose(): Char {
        when(this) {
            'A' -> return 'Z'
            'B' -> return 'X'
            'C' -> return 'Y'
        }
        return '.'
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val p1 = it.first()
            var p2 = '.'
            when (it.last()) {
                'X' -> p2 = p1.lose()
                'Y' -> p2 = p1.draw()
                'Z' -> p2 = p1.win()
            }

            var s = 0
            when (p2) {
                'X' -> s += 1
                'Y' -> s += 2
                'Z' -> s += 3
            }
            when (p1.plus(" ").plus(p2)) {
                "A X", "B Y", "C Z" -> s += 3
                "A Y", "B Z", "C X" -> s += 6
            }
            s
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    val testResult = part1(testInput)
    val testExpected = 15
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    val testResult2 = part2(testInput)
    val testExpected2 = 12
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
