package randomPoint

import java.lang.Math

data class Solution(val radius: Double, val x_center: Double, val y_center: Double) {

    fun randPoint(): DoubleArray {
        while (true) {
            val dx = radius * 2 * (0.5 - Math.random())
            val dy = radius * 2 * (0.5 - Math.random())
            if ((dx * dx + dy * dy) < radius * radius) {
                return doubleArrayOf(x_center + dx, y_center + dy)
            }
        }
    }
}

fun main(args: Array<String>) {
    val s = Solution(10.0, 0.0, 0.0)
    for (i in 1..20) {
        s.randPoint().let { (x, y) ->
            println("% 4.2f % 4.2f % 4.2f".format(x, y, x * x + y * y))
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Solution(radius, x_center, y_center)
 * var param_1 = obj.randPoint()
 */