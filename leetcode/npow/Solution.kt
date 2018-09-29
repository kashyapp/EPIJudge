package npow

class Solution {
    fun foo(x: Double, n: Int, y: Double, res: Double): Double {
        if (n == 0)
            return res
        val next = if (n and 1 == 1) res * y else res
        return foo(x, n shr 1, y * y, next)
    }

    fun myPow(x: Double, n: Int): Double {
        if (n < 0)
            return 1/myPow(x, -n)
        else if (n == 0)
            return 1.0
        else
            return foo(x, n, x, 1.0)
    }
}

fun main(args: Array<String>) {
    println()
}
