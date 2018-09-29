package epi.kashyap

import kotlin.concurrent.thread
import kotlin.math.max
import kotlin.test.assertEquals

fun levenshteinDistanceJ(A: String, B: String): Int {
    return levenshteinDistance(A, 0, B, 0)
}
fun levenshteinDistance(A: String, offsetA: Int, B: String, offsetB: Int, cache: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()): Int {
    cache[offsetA to offsetB]?.let { return it }

    if (offsetA == A.length || offsetB == B.length)
        return max(A.length - offsetA, B.length - offsetB)
thread {  }
    return min(
            (if (A[offsetA] ==  B[offsetB]) 0 else 1) + levenshteinDistance(A, offsetA + 1, B, offsetB + 1, cache)
            , 1 + levenshteinDistance(A, offsetA + 1, B, offsetB, cache)
            , 1 + levenshteinDistance(A, offsetA, B, offsetB + 1, cache)
    ).apply {
        cache[offsetA to offsetB] = this
        println("${A.substring(offsetA)}, ${B.substring(offsetB)} $this")
    }
}

fun min(vararg x: Int) = x.min()!!

fun main(args: Array<String>) {
//    assertEquals(1, levenshteinDistanceJ("hello", "hexllo"))
//    assertEquals(5, levenshteinDistanceJ("hello",""))
//    assertEquals(5, levenshteinDistanceJ("hello", "alpha"))
//    assertEquals(2, levenshteinDistanceJ("ape", "vae"))
    assertEquals(4, levenshteinDistanceJ("krishala", "kaivalya"))
//    assertEquals(3, levenshteinDistanceJ("krish", "kaiv"))
}

