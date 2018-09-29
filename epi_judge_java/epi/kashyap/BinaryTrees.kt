package epi.kashyap

import java.util.*

data class BinaryTree<T>(val value: T, val left: BinaryTree<T>? = null, val right: BinaryTree<T>? = null) {
    /**
     * deserialize
     * serialize
     * depth
     * nth inorder
     * traversals
     * vertical level order
     */
    companion object {
        fun <T> deserialize(s: String): BinaryTree<T>? {
            val scanner = Scanner(s)
            return null
        }

        fun <T> serialize(t: BinaryTree<T>?): String =
                if (t != null) {
                    "(${t.value} ${serialize(t.left)} ${serialize(t.right)})"
                } else {
                    "()"
                }

    }
}

typealias BI = BinaryTree<Int>

fun main(args: Array<String>) {
    val bt = BI(10, BI(5), BI(6, BI(7, BI(8)), BI(10)))
    println(BinaryTree.serialize(bt))
}