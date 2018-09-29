package epi.kashyap

fun removeInvalidParentheses(s: String) = fix(fix(s, '(').reversed(), ')').reversed().apply { println("${s.length}, ${this.length}") }

fun fix(s: String, b: Char): String {
    val sb = StringBuilder()
    var count = 0;
    for (c in s) {
        if (c == b)
            count++
        else
            count--
        if (count >= 0) {
            sb.append(c)
        } else {
            count = 0
        }
    }
    return sb.toString()
}

fun main(args: Array<String>) {
    println(removeInvalidParentheses("(()"))
    println(removeInvalidParentheses("(())"))
    println(removeInvalidParentheses("(()((((()())"))
}