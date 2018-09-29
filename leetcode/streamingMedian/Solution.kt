package streamingMedian

class MedianFinder() {
    data class Pair(val num: Int, val index: Int): Comparable<Pair> {
        override fun compareTo(that: Pair): Int {
            if (this.num.equals(that.num)) {
                return this.index.compareTo(that.index)
            } else {
                return this.num.compareTo(that.num)
            }
        }

        override fun toString(): String {
            return "$num@$index]"
        }
    }

    var n = 0
    val lower = sortedSetOf<Pair>()
    val upper = sortedSetOf<Pair>()

    fun addNum(num: Int) {
        n++
        if (n == 1) {
            upper.add(Pair(num, n))
            return
        }
        if (num < upper.first().num) {
            lower.add(Pair(num, n))
        } else {
            upper.add(Pair(num, n))
        }
        if ((lower.size - upper.size) == 1) {
            val last = lower.pollLast()
            upper.add(last)
        } else if ((upper.size - lower.size) == 2) {
            val first = upper.pollFirst()
            lower.add(first)
        }
    }

    fun findMedian(): Double {
        println("Lower: ${lower}\nUpper: ${upper}")
        if (lower.size == upper.size) {
            return (lower.last().num + upper.first().num) / 2.0
        } else {
            return upper.first().num.toDouble()
        }
    }

}

fun main(args: Array<String>) {
    val mf = MedianFinder()
    arrayOf(5, 4, 3, 2, 1, 2, 3, 4, 5).forEach {
        mf.addNum(it)
        println(mf.findMedian())
    }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */