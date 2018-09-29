package epi.kashyap

import java.util.*

fun offlineSampling(k: Int, A: MutableList<Int>) {
    val r = Random()
    var n = A.size
    for (i in 0 until k) {
        Collections.swap(A, i, i + r.nextInt(n - i))
    }
}

class KRandom {
    fun nextInt(bound: Int) = 4
}

fun main(args: Array<String>) {
    val a = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8)
    offlineSampling(8, a)
    println(a)
}
