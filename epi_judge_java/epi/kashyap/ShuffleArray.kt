package epi.kashyap

import java.util.*

data class Shuffle(val nums: IntArray) {
    val original = nums.copyOf()
    private val r = Random()

    /** Resets the array to its original configuration and return it. */
    fun reset(): IntArray {
        original.forEachIndexed { i, v -> nums[i] = v }
        return original
    }

    /** Returns a random shuffling of the array. */
    fun shuffle(): IntArray {
        for (i in nums.indices) {
            val j = r.nextInt(nums.size - i)
            val t = nums[i + j]
            nums[i + j] = nums[i]
            nums[i] = t
        }
        return nums
    }
}

fun main(args: Array<String>) {
    val s = Shuffle(intArrayOf(1, 2, 3, 4, 5))
//    arrayOf(s::reset, s::shuffle, s::shuffle, s::reset, s::shuffle).forEach {
//        println(it().asList())
//    }

    for (i in 1..1000) {
        s.reset()
        println(s.shuffle().asList())
    }
}