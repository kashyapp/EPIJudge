package dominoTiling

class Solution {
    private tailrec fun foo(N: Int, n: Int, p1: Long, f2: Long, f1: Long): Int {
        println("%10d %10d %10d %10d".format(n, p1, f2, f1))
        if (n == N) {
            return (f1 % 1000000007).toInt()
        } else {
            return foo(N, n+1, (p1 + f2) % 1000000007, f1 % 1000000007, (f1 + f2) % 1000000007 + (2 * p1) % 1000000007)
        }
    }
    fun numTilings(N: Int): Int {
        if (N == 1) {
            return 1
        } else {
            return foo(N, 2, 1, 1, 2)
        }
    }
}

fun main(args: Array<String>) {
    val x = Solution()
    for (i in 1..100)
        println("$i, ${x.numTilings(i)}")
}