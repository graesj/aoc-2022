sealed class Move(val points: Int)
object Rock : Move(points = 1)
object Paper : Move(points = 2)
object Scissor : Move(points = 3)

sealed class GameResult(val points: Int)
object Victory : GameResult(points = 6)
object Draw : GameResult(points = 3)
object Loss : GameResult(points = 0)

fun charToMove(char: Char): Move {
    return when (char) {
        'A', 'X' -> Rock
        'B', 'Y' -> Paper
        'C', 'Z' -> Scissor
        else -> throw RuntimeException("invalid input")
    }
}

fun charToDesiredGameResult(char: Char): GameResult {
    return when (char) {
        'X' -> Loss
        'Y' -> Draw
        'Z' -> Victory
        else -> throw RuntimeException("invalid input")
    }
}

fun game(opponentMove: Move, yourMove: Move): GameResult {
    if (opponentMove == yourMove) return Draw
    if (opponentMove == Rock) {
        return if (yourMove == Paper) Victory else Loss
    }
    if (opponentMove == Paper) {
        return if (yourMove == Scissor) Victory else Loss
    }
    return if (yourMove == Rock) Victory else Loss
}

fun determineMove(opponentMove: Move, desiredGameResult: GameResult): Move {
    when (desiredGameResult) {
        Draw -> return opponentMove
        Victory -> {
            if (opponentMove == Paper) return Scissor
            if (opponentMove == Rock) return Paper
            return Rock
        }
        Loss -> {
            if (opponentMove == Paper) return Rock
            if (opponentMove == Rock) return Scissor
            return Paper
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val split = it.split(" ")
            charToMove(split[0].first()) to charToMove(split[1].first())
        }.fold(0) { acc, pair: Pair<Move, Move> ->
            val (opponentMove, yourMove) = pair
            acc + game(opponentMove, yourMove).points + yourMove.points
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            val split = it.split(" ")
            charToMove(split[0].first()) to charToDesiredGameResult(split[1].first())
        }.fold(0) { acc, pair: Pair<Move, GameResult> ->
            val (opponentMove, desiredGameResult) = pair
            acc + desiredGameResult.points + determineMove(opponentMove, desiredGameResult).points
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}



