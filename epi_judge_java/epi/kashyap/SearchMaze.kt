package epi.kashyap
import epi.SearchMaze.Color
import epi.SearchMaze.Coordinate
import java.util.*

fun Coordinate.neighbours() = sequenceOf(
        Coordinate(x, y + 1),
        Coordinate(x + 1, y ),
        Coordinate(x, y - 1),
        Coordinate(x - 1, y)
)


data class Maze(val maze: List<List<Color>>) {
    fun neighbours(c: Coordinate) = c.neighbours()
            .filter { it.x in xRange && it.y in yRange }
            .filter { maze[it.x][it.y] == Color.WHITE}

    val xRange = 0 until maze.size
    val yRange = 0 until maze[0].size
}

fun searchMaze(maze: MutableList<MutableList<Color>>, start: Coordinate, end: Coordinate): List<Coordinate> {
    val m = Maze(maze)
    val q = LinkedList<Coordinate>()

    maze[start.x][start.y] = Color.BLACK
    q.add(start)
    while (q.isNotEmpty()) {
        val current = q.last()
        if (current == end) {
//            println(q)
            return q
        }

        val next = m.neighbours(current).firstOrNull()
        if (next == null) {
            q.removeLast()
        } else {
            maze[next.x][next.y] = Color.BLACK
            q.add(next)
        }
    }

    return listOf()
}

fun main(args: Array<String>) {
    Coordinate(0, 0).neighbours().forEach { println(it) }
}