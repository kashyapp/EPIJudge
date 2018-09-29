package lruCache

class LRUCache(capacity: Int) {
    val map = object: LinkedHashMap<Int, Int>(capacity, 0.75F, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Int, Int>?): Boolean {
            return size > capacity
        }
    }

    fun get(key: Int): Int {
        return map.getOrDefault(key, -1)
    }

    fun put(key: Int, value: Int) {
        map.put(key, value)
    }

}

fun main(args: Array<String>) {
    val cache = LRUCache(3)
    cache.put(1, 1)
    cache.put(2, 2)
    cache.put(3, 3)
    println(cache.get(1))
    cache.put(4, 4)
    println(cache.get(2))
    println(cache.get(3))
    println(cache.get(4))
    println(cache.get(1))
    cache.put(2, 2)
    println(cache.get(2))
    println(cache.get(3))
    println(cache.get(4))
    println(cache.get(1))
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */