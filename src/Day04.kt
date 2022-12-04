fun IntRange.size(): Int = this.last - this.first

fun stringToSections(it: String): Pair<IntRange, IntRange> {
    val split = it.split("-")
    return split.first().toInt()..split[1].substringBefore(",").toInt() to
            split[1].substringAfter(",").toInt()..split.last().toInt()
}

fun partitionBySize(rangePair: Pair<IntRange, IntRange>): Pair<IntRange, IntRange> {
    val (firstRange, secondRange) = rangePair
    return if (firstRange.size() <= secondRange.size()) firstRange to secondRange else secondRange to firstRange
}

fun main() {

    fun part1(input: List<String>): Int {
        return input.map {
            stringToSections(it)
        }.count { sectionPair ->
            val (smallestSection, largestSection) = partitionBySize(sectionPair)
            smallestSection.first >= largestSection.first && smallestSection.last <= largestSection.last
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            stringToSections(it)
        }.count { sectionPair ->
            val (smallestSection, largestSection) = partitionBySize(sectionPair)
            largestSection.contains(smallestSection.first) || largestSection.contains(smallestSection.last)
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
