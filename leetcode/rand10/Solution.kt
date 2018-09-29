package rand10

class Solution {
    fun rand10(): Int {
        while (true) {
            val n = rand7() + rand7() - 2
            if (n < 10) return n + 1
        }
    }

    private fun rand7(): Int {
        return 1
    }
}

fun main(args: Array<String>) {
    val s = Solution()
    println(s.rand10())
}