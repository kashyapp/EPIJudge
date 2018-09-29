package epi.kashyap
import epi.DutchNationalFlag.Color
import java.util.*

fun <T: Comparable<T>> dutchFlagPartition(pivotIndex: Int, a: MutableList<T>) {
    val pivot = a[pivotIndex]
    var lesser = 0
    for (i in a.indices) {
        if (a[i] <= pivot) {
            Collections.swap(a, lesser++, i)
        }
    }
    lesser = 0
    for (i in a.indices) {
        if (a[i] < pivot) {
            Collections.swap(a, lesser++, i)
        }
    }
}

fun <T: Comparable<T>>dutchFlagPartition2(pivotIndex: Int, a: MutableList<T>) {
    // a is empty?

    val pivot = a[pivotIndex]
    arrayOf(0, -1).forEach {
        var left = 0
        var right = a.size - 1
        while (left < right) {
            while (pivot.compareTo(a[left]) > it && left < right) {
                left++
            }
            while (pivot.compareTo(a[right]) <= it && left < right) {
                right--
            }
            if (left < right) {
                Collections.swap(a, left, right)
            }
        }
    }
}

fun main(args: Array<String>) {
    val a = arrayListOf(9, 8, 7, 6, 6, 6, 6, 5, 4, 3, 2, 1, 6, 6, 6)
    dutchFlagPartition(3, a)
    println(a)
}