fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { it.toIntOrNull() }
            .runningFold(0) { acc, calories ->
                calories?.let {
                    acc + it
                } ?: 0
            }.max()
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.toIntOrNull() }
            .runningFold(0) { acc, calories ->
                calories?.let {
                    acc + it
                } ?: 0
            }.sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readLines("Day01_test")
    check(part1(testInput) == 24000)

    val input = readLines("Day01")
    println(part1(input))
    println(part2(input))
}
