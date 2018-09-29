package epi.kashyap

fun evenOdd(arr: MutableList<Int>) {
    var left = 0
    var right = arr.size - 1
    while (left < right) {
        while (left < arr.size && arr[left]  % 2 == 0) left++
        while (right >= 0 && arr[right] % 2 == 1) right--
        if (left < right) {
            val t = arr[left]
            arr[left] = arr[right]
            arr[right] = t
        }
    }
}

fun main(args: Array<String>) {
    val x = arrayListOf<Int>()
    evenOdd(x)
    println(x)
}