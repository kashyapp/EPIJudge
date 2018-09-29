package epi.kashyap

import java.util.*
import kotlin.test.assertEquals

fun findMin(A: List<Int>, start: Int, end: Int): Int {
    if (end - start == 1) return A[start]
    val mid = start + (end - start) / 2
    if (mid > start && A[mid] > A[mid - 1]) return findMin(A, start, mid)
    if (mid < end - 1 && A[mid] > A[mid + 1]) return findMin(A, mid, end)
    return A[mid]
}

fun main(args: Array<String>) {
    assertEquals(3, findMin(listOf(3), 0, 1))
    assertEquals(3, findMin(listOf(3, 4, 5, 6, 7), 0, 5))
    assertEquals(0, findMin(listOf(3, 2, 1, 0), 0, 4))
    assertEquals(0, findMin(listOf(3, 2, 1, 0, 1, 2, 3), 0, 7))
    assertEquals(1, findMin(listOf(3, 2, 1, 1, 1, 2, 3), 0, 7))
    assertEquals(1, findMin(listOf(3, 2, 1, 5, 1, 2, 3), 0, 7))
    assertEquals(1, findMin(listOf(3, 2, 1, 1, 1, 2, 3), 0, 7))
    assertEquals(0, findMin(listOf(3, 2, 0, 1, 2, 2, 3), 0, 7))
    assertEquals(2, findMin(listOf(3, 2, 2, 2, 2, 2, 0), 0, 7))

    assertEquals(3, thirdLargest(listOf(1, 2, 3, 4, 5)))
    assertEquals(1, thirdLargest(listOf(1, 2, 3)))
    assertEquals(4, thirdLargest(listOf(4, 4, 4)))
    assertEquals(4, thirdLargest(listOf(4, 4, 4, 4, 4, 4)))
    assertEquals(4, thirdLargest(listOf(4, 6, 4, 4, 5, 4)))
}

fun thirdLargest(A: List<Int>): Int {
    for (i in 0..2) {
        var max = i
        for (j in i until A.size) {
            if (A[j] > A[max]) max = j
        }
        Collections.swap(A, i, max)
    }
    println(A)
    return A[2]
}