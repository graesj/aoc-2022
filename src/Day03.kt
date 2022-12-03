fun Char.getScore(): Int {
    return if (this.isLowerCase()) this - 'a' + 1 else this - 'A' + 27
}

fun main() {
    operator fun List<Set<Char>>.component1() = this.first()
    operator fun List<Set<Char>>.component2() = this[1]
    operator fun List<Set<Char>>.component3() = this[2]

    fun part1(input: List<String>): Int {
        return input.sumOf { racksack ->
            val compartments: List<Set<Char>> = racksack.chunked(racksack.length / 2).map { it.toCharArray().toSet() }
            val (compartmentOne, compartmentTwo) = compartments
            val intersection = compartmentOne intersect compartmentTwo
            intersection.first().getScore()
        }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toCharArray().toSet() }.chunked(3).sumOf { group ->
            val (rsOne, rsTwo, rsThree) = group
            val intersection = (rsOne intersect rsTwo) intersect rsThree
            intersection.first().getScore()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
