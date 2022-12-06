fun main() {
    fun findMessage(input: String, length: Int): Int {
        for (el in input.windowed(length).withIndex()) {
            if (el.value.length == el.value.toSet().size) {
                return el.index + length
            }
        }
        return -1
    }

    val day = "06"

    // Read inputs
    val testInput = readText("Day${day}_test")
    val input = readText("Day${day}")

    // Test & run part 1
    val testResult = findMessage(testInput, 4)
    val testExpected = 7
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }
    println(findMessage(input, 4))

    // Test & run part 2
    val testResult2 = findMessage(testInput, 14)
    val testExpected2 = 19
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }
    println(findMessage(input, 14))
}