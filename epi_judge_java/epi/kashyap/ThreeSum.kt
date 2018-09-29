package epi.kashyap

import kotlin.test.assertEquals

fun hasThreeSum(A: List<Int>, t: Int) = with(A.sorted()) {
    any {
        hasTwoSum(this, t - it)
    }
}


fun hasTwoSum(A: List<Int>, t: Int): Boolean {
    var left = 0
    var right = A.lastIndex
    while (left <= right) {
        val sum = A[left] + A[right]
        if (sum == t) return true
        when {
            sum == t -> return true
            sum < t -> left++
            else -> right--
        }
    }
    return false
}

fun main(args: Array<String>) {
    assertEquals(true, hasTwoSum(listOf(2, 3, 5, 7, 11), 10))
    assertEquals(true, hasTwoSum(listOf(2, 3, 5, 7, 11), 14))
    assertEquals(false, hasTwoSum(listOf(2, 3, 5, 7, 11), 11))

    assertEquals(true, hasThreeSum(listOf(2, 3, 5, 7, 11), 11))
    assertEquals(true, hasThreeSum(listOf(2, 3, 5, 7, 11), 21))
    assertEquals(false, hasThreeSum(listOf(2, 3, 5, 7, 11), 22))
}
