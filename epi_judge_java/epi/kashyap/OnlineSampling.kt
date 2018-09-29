package epi.kashyap

import java.util.Random

fun onlineRandomSample(stream: Iterator<Int>, k: Int): List<Int> {
    val sample = ArrayList<Int>(k)
    var n = 0
    for (i in 1..k) {
        if (stream.hasNext()) {
            sample.add(stream.next())
            n++
        }
    }

    val r = Random()
    while (stream.hasNext()) {
        n++
        val i = r.nextInt(n)
        if (i < k) {
            sample[i] = stream.next()
        }
    }

    return sample
}