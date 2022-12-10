import kotlin.math.min

fun main() {

    fun List<List<String>>.transpose(): MutableList<MutableList<String>> {
        val colList = mutableListOf<MutableList<String>>()
        (0..this.last().size).map { col ->
            colList.add(col, mutableListOf())
            this.indices.forEach { height ->
                val element = this[height].getOrElse(col) { "" }
                if (element.isNotBlank()) {
                    colList[col].add(element)
                }
            }
        }
        return colList
    }

    val instructionRegex = """move ([0-9]+) from ([0-9]+) to ([0-9]+)""".toRegex()

    fun getInstructionStartIndexAndBoxes(input: List<String>): Pair<Int, MutableList<MutableList<String>>> {
        val lines = input
            .takeWhile { instructionRegex.matches(it).not() }
            .filterNot { it.isEmpty() }
            .map { it.windowed(size = 4, step = 4, partialWindows = true) }
        val instructionStartIndex = lines.size + 1
        val boxes: MutableList<MutableList<String>> = lines.transpose()
        return Pair(instructionStartIndex, boxes)
    }

    fun part1(input: List<String>): String {
        val (instructionStartIndex, boxes) = getInstructionStartIndexAndBoxes(input)

        input.drop(instructionStartIndex).forEach {
            val (amount, fromCol, toCol) = instructionRegex.matchEntire(it)!!.destructured
            val columnToTakeFrom = boxes[fromCol.toInt() - 1]
            val boxesToMove = columnToTakeFrom.subList(0, min(amount.toInt(), columnToTakeFrom.size))
            boxes[toCol.toInt() - 1].addAll(0, boxesToMove.reversed())
            boxesToMove.clear()
        }

        return boxes.dropLast(1).joinToString("") { it.first().trim().removeSurrounding("[", "]") }
    }

    fun part2(input: List<String>): String {
        val (instructionStartIndex, boxes) = getInstructionStartIndexAndBoxes(input)
        input.drop(instructionStartIndex).forEach {
            val (amount, fromCol, toCol) = instructionRegex.matchEntire(it)!!.destructured
            val columnToTakeFrom = boxes[fromCol.toInt() - 1]
            val boxesToMove = columnToTakeFrom.subList(0, min(amount.toInt(), columnToTakeFrom.size))
            boxes[toCol.toInt() - 1].addAll(0, boxesToMove)
            boxesToMove.clear()
        }

        return boxes.dropLast(1).joinToString("") { it.first().trim().removeSurrounding("[", "]") }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readLines("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readLines("Day05")
    println(part1(input))
    println(part2(input))
}
