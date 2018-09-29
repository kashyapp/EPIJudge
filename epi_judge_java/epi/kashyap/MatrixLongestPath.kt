package epi.kashyap

import java.util.*

class MatrixPath {
    data class Coord(val x: Int, val y: Int) {
        fun adjacents() = listOf(
                Coord(x, y + 1),
                Coord(x, y - 1),
                Coord(x + 1, y),
                Coord(x - 1, y))
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    fun longestIncreasingPath(matrix: Array<IntArray>): Int {
        val xRange = 0 until matrix.size
        if (xRange.isEmpty()) return 0

        val yRange = 0 until matrix[0].size
        if (yRange.isEmpty()) return 0

        val unvisited = HashSet(xRange.map { x -> yRange.map{ y -> Coord(x, y)}}.flatten())
        val stepMatrix = Array(matrix.size) { IntArray(matrix[0].size) {-1} }

        while (unvisited.isNotEmpty()) {
            val root = unvisited.first()

            val stack = Stack<Coord>()
            stack.add(root)
            unvisited.remove(root)

            while (stack.isNotEmpty()) {
                val cell = stack.peek()
                val cellValue = matrix[cell.x][cell.y]

                val adj = cell.adjacents().filter { (x, y) -> x in xRange && y in yRange && matrix[x][y] > cellValue}
                val next = adj.firstOrNull { (x, y) -> stepMatrix[x][y] == -1}
                if (next == null) {
                    val steps = 1 + (adj.map { (x, y) -> stepMatrix[x][y] }.max() ?: 0)
                    stepMatrix[cell.x][cell.y] = steps
                    stack.pop()
                } else {
                    stack.add(next)
                    unvisited.remove(next)
                }
            }
        }

        return stepMatrix.map { it.max()!! }.max()!!
    }
}
