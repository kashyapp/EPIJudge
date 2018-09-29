package epi.kashyap

import epi.DrawingSkyline

class Skyline {
    data class Point(val x: Int, val y: Int, val buildingId: Int, val isOpen: Boolean) : Comparable<Point> {
        override fun compareTo(other: Point): Int = this.x.compareTo(other.x)
    }

    fun getSkyline(buildings: Array<IntArray>): List<IntArray> {
        val points = arrayListOf<Skyline.Point>()
        for (i in buildings.indices) {
            val b = buildings[i]
            points.add(Skyline.Point(b[0], b[2], i, true))
            points.add(Skyline.Point(b[1], b[2], i, false))
        }
        val sorted = points.sorted()
        val keyPoints = arrayListOf<IntArray>()

        val ws = mutableSetOf<Int>()

        keyPoints.add(intArrayOf(Int.MIN_VALUE, 0))
        for (p in sorted) {
            if (p.isOpen) {
                ws.add(p.buildingId)
                addKeyPoint(keyPoints, p.x, findHighest(buildings, ws).let { id -> buildings[id][2] })
            } else {
                ws.remove(p.buildingId)
                if (ws.isEmpty()) {
                    addKeyPoint(keyPoints, p.x, 0)
                } else {
                    addKeyPoint(keyPoints, p.x, findHighest(buildings, ws).let {id -> buildings[id][2]})
                }
            }
        }
        return keyPoints.subList(1, keyPoints.size)
    }

    private fun findHighest(buildings: Array<IntArray>, ws: Set<Int>): Int = ws.maxBy { i -> buildings[i][2] }!!

    private fun addKeyPoint(keyPoints: ArrayList<IntArray>, x: Int, y: Int) {
        val last = keyPoints.last()
        if (x == last[0]) {
            keyPoints.removeAt(keyPoints.size - 1)
            keyPoints.add(intArrayOf(x, y))
        }
        else if (y != last[1]) {
            keyPoints.add(intArrayOf(x, y))
        }
    }

    fun epiShim(buildings: List<DrawingSkyline.Rectangle>): List<DrawingSkyline.Rectangle> {
        return buildings
                .map { intArrayOf(it.left, it.right, it.height) }
                .let { getSkyline(it.toTypedArray()) }
                .map { DrawingSkyline.Rectangle(it[0], 0, it[1])}
    }
}

fun main(args: Array<String>) {
    val s = Skyline()
    run {
        val result = s.getSkyline(arrayOf(
                intArrayOf(1, 3, 4),
                intArrayOf(2, 4, 2),
                intArrayOf(3, 6, 3),
                intArrayOf(3, 5, 2)
        ))
        result.forEach { v -> println(Pair(v[0], v[1])) }
        println("----")
    }
    run {
        val result = s.getSkyline(arrayOf(
                intArrayOf(1, 3, 4),
                intArrayOf(2, 4, 2),
                intArrayOf(3, 5, 2),
                intArrayOf(3, 6, 3)
        ))
        result.forEach { v -> println(Pair(v[0], v[1])) }
        println("----")
    }

    run {
        val result = s.getSkyline(arrayOf(
                intArrayOf(2, 9 , 10),
                intArrayOf(3, 7, 15),
                intArrayOf(5, 12, 12),
                intArrayOf(15, 20, 10),
                intArrayOf(19, 24, 8)
        ))
        result.forEach { v -> println(Pair(v[0], v[1])) }
        println("----")
    }
}
