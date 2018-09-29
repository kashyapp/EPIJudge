package epi.kashyap

import java.util.*

data class Coordinate(val x: Int, val y: Int) {
    fun adjacents() = listOf(
            Coordinate(x, y + 1),
            Coordinate(x, y - 1),
            Coordinate(x + 1, y),
            Coordinate(x - 1, y)
    )
}

fun flipColor(x: Int, y: Int, matrix: MutableList<MutableList<Boolean>>) {
    val q: Queue<Coordinate> = ArrayDeque<Coordinate>()
    val colour = matrix[x][y]

    matrix[x][y] = !colour
    q.add(Coordinate(x, y))

    while (q.isNotEmpty()) {
        q.poll()
                .adjacents()
                .filter { (x, y) ->
                    x in 0 until matrix.size
                    && y in 0 until matrix[0].size
                    && matrix[x][y] == colour
                }
                .onEach { (x, y) ->
                    matrix[x][y] = !colour
                }
                .forEach {
                    q.offer(it)
                }
    }
}