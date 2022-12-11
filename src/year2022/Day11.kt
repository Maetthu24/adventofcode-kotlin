package year2022

fun main() {
    data class Monkey(val items: MutableList<Long>, val operation: (Long) -> Long, val divisibleBy: Long, val ifTrue: Int, val ifFalse: Int) {
        fun test(nr: Long): Int {
            return if(nr % divisibleBy == 0.toLong()) ifTrue else ifFalse
        }
    }

    fun createMonkey(input: List<String>): Monkey {
        val items = input[1].drop(18).split(", ").map { it.toLong() }
        val opString = input[2].drop(13).split(" ").drop(2)
        val divisibleBy = input[3].split(" ").last().toLong()
        val ifTrue = input[4].split(" ").last().toInt()
        val ifFalse = input[5].split(" ").last().toInt()

        return Monkey(items.toMutableList(), { old ->
            val first = if (opString[0] == "old") old else opString[0].toLong()
            val second = if (opString[2] == "old") old else opString[2].toLong()
            when (opString[1]) {
                "+" -> first + second
                "-" -> first - second
                "*" -> first * second
                "/" -> first / second
                else -> error("Unsupported operator")
            }
        }, divisibleBy, ifTrue, ifFalse)
    }

    fun part1(input: String): Int {
        val monkeys = input.split("\n\n").map { createMonkey(it.split("\n")) }
        val activity: Map<Int, Int> = buildMap {
            monkeys.indices.forEach { put(it, 0) }
            for (round in 1 .. 20) {
                for ((idx, monkey) in monkeys.withIndex()) {
                    for (item in monkey.items) {
                        val c: Int = get(idx)!!
                        put(idx, c + 1)
                        val new = monkey.operation(item) / 3
                        val newMonkeyIdx = monkey.test(new)
                        monkeys[newMonkeyIdx].items.add(new)
                    }
                    monkey.items.clear()
                }
            }
        }
        return activity.values.sortedDescending()[0] * activity.values.sortedDescending()[1]
    }

    fun gcd(a: Long, b: Long): Long {
        var aa = a
        var bb = b
        while (bb > 0) {
            val temp = bb
            bb = aa % bb
            aa = temp
        }
        return aa
    }

    fun lcm(a: Long, b: Long): Long {
        return a * (b / gcd(a, b))
    }

    fun lcm(input: List<Long>): Long {
        var result = input[0]
        for (i in 1 until input.size) {
            result = lcm(result, input[i])
        }
        return result
    }

    fun part2(input: String): Long {
        val monkeys = input.split("\n\n").map { createMonkey(it.split("\n")) }
        val lcm = lcm(monkeys.map { it.divisibleBy })
        val activity: Map<Int, Long> = buildMap {
            monkeys.indices.forEach { put(it, 0) }
            for (round in 1 .. 10_000) {
                for ((idx, monkey) in monkeys.withIndex()) {
                    for (item in monkey.items) {
                        val c: Long = get(idx)!!
                        put(idx, c + 1)
                        val new = monkey.operation(item) % lcm
                        val newMonkeyIdx = monkey.test(new)
                        monkeys[newMonkeyIdx].items.add(new)
                    }
                    monkey.items.clear()
                }
            }
        }
        return activity.values.sortedDescending()[0] * activity.values.sortedDescending()[1]
    }

    val day = "11"

    // Read inputs
    val testInput = readText("Day${day}_test")
    val input = readText("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 10605
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(part1(input))

    // Test & run part 2
    val testResult2 = part2(testInput)
    val testExpected2 = 2713310158
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    println(part2(input))
}