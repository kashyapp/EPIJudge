package epi.kashyap

import kotlin.math.max

fun longestSubstring1(s: String, k: Int): Int {
    if (s.isEmpty()) return 0

    val bad = s.groupingBy { it }.eachCount().filter { (c, count) -> count < k }

    if (bad.isEmpty()) {
//        println(s)
        return s.length
    }

    var maxLen = 0

    var i = 0
    while (i < s.length) {
        if (s[i] in bad) {
            i++
        } else {
            var j = i
            while (j < s.length && s[j] !in bad) j++
            maxLen = max(maxLen, longestSubstring1(s.substring(i, j), k))
            i = j
        }
    }

    return maxLen
}

fun longestSubstring2(s: String, k: Int): Int {
    println(">>>> $s $k")
    return foo(s, 0, s.length, k, 0, k)
}
fun foo(s: String, start: Int, end: Int, k: Int, depth: Int, lowerBound: Int): Int {
    println("${"   |".repeat(depth)} $start, $end, $lowerBound")

    if (end - start < k) return 0

    val counts = Array(26, {0})
    for (i in start until end) {
        val c = s[i]
        counts[c - 'a']++
    }

    var maxLen = 0

    var i = start
    while (i < end) {
        if (counts[s[i] - 'a'] < k) {
            i++
        } else {
            var j = i
            while (j < end && counts[s[j] - 'a'] >= k) j++
            if (i == start && j == end) return end - start
            maxLen = max(maxLen, foo(s, i, j, k, depth + 1, max(maxLen, lowerBound)))
            i = j
        }
    }

    return maxLen
}


fun main(args: Array<String>) {
    val longestSubstring = { s: String, k: Int -> println(longestSubstring2(s, k))}
    longestSubstring("a", 2)
    longestSubstring("abcdabcdeabc", 1)
    longestSubstring("abcdabcdeabc", 2)
    longestSubstring("abcfdaabcdeabc", 2)
    longestSubstring("abcdabcdeabc", 3)
    longestSubstring("abcdabcdeabc", 4)


}