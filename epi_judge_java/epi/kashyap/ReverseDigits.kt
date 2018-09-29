package epi.kashyap

fun reverse(_x: Int): Long {
    var x = Math.abs(_x)
    var y = 0L
    while (x > 0) {
        y = y * 10 + x % 10
        x = x / 10
    }
    return y * if (_x < 0) -1 else 1
}