package MaxSumSubarray

class Solution {
    fun find(arr: List<Int>): Result {
        return find2(arr, 0).second
    }

    private fun find2(arr: List<Int>, i: Int): Pair<Result, Result> {
        if (i == arr.size) {
            return Pair(Result(i, i, -1), Result(i, i, -1))
        }
        val (left, other) = find2(arr, i+1)
        val thisLeft = if (left.sum > 0) {
            Result(i, left.end, arr[i] + left.sum)
        } else {
            Result(i, i+1, arr[i])
        }
        val thisOther = if (thisLeft.sum > other.sum) {
            thisLeft
        } else {
            other
        }
        return Pair(thisLeft, thisOther)
    }

    data class Result(val start: Int, val end: Int, val sum: Int)

}

fun main(args: Array<String>) {
    val s = Solution()
    println(s.find(listOf(31, -41, 59, 26, -53, 58, 97, -93, -23, 84)))
}