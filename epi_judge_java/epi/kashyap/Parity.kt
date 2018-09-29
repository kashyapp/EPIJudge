package epi.kashyap

fun parity(x: Long) = parity3(x)

fun parity1(_x: Long): Short {
    var x = _x
    var count = 0L
    while (x > 0) {
        count += x and 1
        x = x ushr 1
    }
    return (count % 2).toShort()
}

fun parity2(_x: Long): Short {
    var x = _x
    return generateSequence {
        if (x > 0L) {
            val b = x and 1
            x = x shr 1
            b
        } else {
            null
        }
    }.sum().let { (it % 2).toShort() }
}

fun parity3(_x: Long): Short {
    var x = _x
    x = x xor (x ushr 32)
    x = x xor (x ushr 16)
    x = x xor (x ushr 8)
    x = x xor (x ushr 4)
    x = x xor (x ushr 2)
    x = x xor (x ushr 1)
    return (x and 1).toShort()
}

fun main(args: Array<String>) {
    val r = 10
    arrayOf(0, 1, 2, -1, -2).forEach {
        println("<<<< $it >>>>")
        println((it shr 1).toString(r))
        println((it ushr 1).toString(r))
        println((it shl 1).toString(r))
    }

    println(77 and ((1 shl 6) - 1))
}