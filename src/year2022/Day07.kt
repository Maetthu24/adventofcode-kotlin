package year2022

fun main() {

    class Dir(val name: String,
              val path: List<String>,
              val files: MutableList<Pair<String, Int>> = mutableListOf(),
              val subDirs: MutableList<Dir> = mutableListOf()) {
        val totalSize: Int
        get() = files.sumOf { it.second } + subDirs.sumOf { it.totalSize }
    }

    var dirs = mutableListOf<Dir>()

    fun part1(input: List<String>): Int {
        dirs = mutableListOf()
        val currentPath = mutableListOf<String>()
        var currentDir: Dir? = null
        for (line in input) {
            val parts = line.split(" ")
            if (parts[0] == "$") {
                if(parts[1] == "cd") {
                    when (parts[2]) {
                        ".." -> currentPath.removeLast()
                        else -> currentPath.add(parts[2])
                    }
                    val p = currentPath.dropLast(1)
                    currentDir = if (dirs.any { it.name == currentPath.last() && it.path == p }) {
                        dirs.first { it.name == currentPath.last() && it.path == p }
                    } else {
                        val d = Dir(currentPath.last(), p)
                        dirs.add(d)
                        d
                    }
                }
            } else {
                if (parts[0] == "dir") {
                    val p = currentPath.toMutableList()
                    val d = Dir(parts[1], p)
                    if (!currentDir!!.subDirs.any { it.name == d.name }) {
                        currentDir.subDirs.add(d)
                    }
                    if (!dirs.any { it.name == d.name && it.path == d.path }) {
                        dirs.add(d)
                    }
                } else {
                    currentDir?.let {
                        if (!it.files.any { it.first == parts[1] }) {
                            it.files.add(Pair(parts[1], parts[0].toInt()))
                        }
                    }
                }
            }
        }

        return dirs.filter { it.totalSize <= 100000 }.sumOf { it.totalSize }
    }

    fun part2(): Int {
        val totalSpace = 70_000_000
        val freeRequired = 30_000_000
        val minNeeded = freeRequired - (totalSpace - dirs.first().totalSize)
        val dirSizes = dirs.map { it.totalSize }.sorted()
        return dirSizes.first { it >= minNeeded }
    }

    val day = "07"

    // Read inputs
    val testInput = readInput("Day${day}_test")
    val input = readInput("Day${day}")

    // Test & run part 1
    val testResult = part1(testInput)
    val testExpected = 95437
    check(testResult == testExpected) { "testResult should be $testExpected, but is $testResult" }

    // Test & run part 2
    val testResult2 = part2()
    val testExpected2 = 24933642
    check(testResult2 == testExpected2) { "testResult2 should be $testExpected2, but is $testResult2" }

    println(part1(input))
    println(part2())
}