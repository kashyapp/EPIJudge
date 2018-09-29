package epi.kashyap

fun numCombinationsForFinalScore(finalScore: Int, individualPlayScores: List<Int>): Int {
    val combinations = Array<Int>(finalScore + 1, {0})
    combinations[0] = 1

    individualPlayScores.forEach { score ->
        for (i in combinations.indices) {
            if (combinations[i] == 0) continue
            val next = i + score
            if (next in combinations.indices) {
                combinations[next] += combinations[i]
            }
        }
    }
    return combinations[finalScore]
}

fun main(args: Array<String>) {
    println(numCombinationsForFinalScore(4, listOf(1, 2, 3)))
}