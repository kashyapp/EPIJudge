package maxSlidingWindow

import java.util.*

class Solution {
    data class Element(val value: Int, val index: Int)

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        val candidates: Deque<Element> = LinkedList<Element>()

        if (nums.isEmpty()) return nums

        val result = IntArray(nums.size + 1 - k)
        for (i in nums.indices) {
            while (candidates.isNotEmpty() && candidates.first.value <= nums[i]) {
                candidates.removeFirst()
            }
            candidates.addFirst(Element(nums[i], i))

            while (candidates.last.index <= (i - k)) {
                candidates.removeLast()
            }

            if ((i - k + 1) >= 0) {
                result[i - k + 1] = candidates.last.value
            }
        }
        return result
    }
}

fun main(args: Array<String>) {
    val s = Solution()
    println(s.maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 3).asList())
    println(s.maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 1).asList())
    println(s.maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 8).asList())
    println(s.maxSlidingWindow(intArrayOf(1,3,-1,-3,5,3,6,7), 7).asList())
    println(s.maxSlidingWindow(intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1), 2).asList())
    println(s.maxSlidingWindow(intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1), 1).asList())
}