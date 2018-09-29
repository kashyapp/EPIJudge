package rotatedDigits

class Solution {
    fun rotatedDigits(N: Int): Int {
        var result = 0
        for (i in 1..N) {
            if (isGood(0, i) > 0) result++
        }
        return result
    }

    private fun isGood(result: Int, n: Int): Int {
        if (n == 0) return result
        val d = n % 10
        return when (d) {
            0, 1, 8 -> isGood(result,n/10)
            2, 5, 6, 9 -> isGood(1,n/10)
            else -> -1
        }
    }
}