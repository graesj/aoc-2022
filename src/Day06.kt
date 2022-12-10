fun main() {

    fun getStartOfMessageMarker(input: String, markerDistinctCharacters: Int) = input.asSequence()
        .windowed(size = markerDistinctCharacters, step = 1)
        .map { it.toSet() }
        .indexOfFirst { it.size == markerDistinctCharacters } + markerDistinctCharacters

    fun part1(input: String): Int {
        return getStartOfMessageMarker(input, markerDistinctCharacters = 4)
    }

    fun part2(input: String): Int {
        return getStartOfMessageMarker(input, markerDistinctCharacters = 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day06_test")
    check(part1(testInput) == 7)

    val input = readText("Day06")
    println(part1(input))
    println(part2(input))
}
