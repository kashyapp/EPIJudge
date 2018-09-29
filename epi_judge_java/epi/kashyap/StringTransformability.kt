package epi.kashyap

import java.util.*

data class Dict(val D: Set<String>) {
    fun neighbours(s: String): List<String> {
        return s.indices.map {i ->
            ('a'..'z').map {c ->
                s.substring(0, i) + c + s.substring(i + 1)
            }.filter { D.contains(it) && s != it}
        }.flatten()
    }
}

fun transformString(D: Set<String>, s: String, t: String): Int {
    val dict = Dict(D)
    val q = ArrayDeque<String>()

    val visited = mutableMapOf<String, Pair<Int, String?>>()
    visited[s] = Pair(0, null)
    q.offer(s)

    while (q.isNotEmpty()) {
        val word = q.poll()
        val (steps, prev) = visited[word]!!

        dict.neighbours(word)
                .filterNot { visited.contains(it) }
                .forEach {
                    visited[it] = Pair(steps + 1, word)
                    q.offer(it)
                }
    }

    visited.forEach { (to, from) ->
        println("${from.second} -> ${to} [label=${from.first}, color = blue]")
    }
    D.forEach { from ->
        dict.neighbours(from)
                .filter { to ->
                    D.indexOf(from) < D.indexOf(to)
                }
                .forEach { to ->
                    println("$from -> $to [color = red]")
                }
    }

    return visited[t]?.let {
        println(generateSequence(it.second, fun(w: String) = visited[w]?.second).toList().asReversed())
        it.first
    } ?: -1

}

fun main(args: Array<String>) {
    val words = setOf("cat", "bat", "cot", "cab", "cub", "cut", "cub", "tub", "tug", "tuf", "lug", "log", "lob", "rob", "rxb", "lot", "bab", "bob")
    val dict = Dict(words)
    println(dict.neighbours("cat"))
    println(transformString(words, "cat", "dog"))
//    println(transformString(words, "cat", "tub"))
//    println(transformString(words, "cat", "lob"))
//    println(transformString(words, "cat", "rob"))
}