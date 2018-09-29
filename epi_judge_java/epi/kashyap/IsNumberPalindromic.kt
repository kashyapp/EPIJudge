package epi.kashyap

class IsNumberPalindromic {
    companion object {
        @JvmStatic
        fun solve(_x: Int): Boolean {
            var x = _x
            var y = 0
            while (x > 0) {
                y = y * 10 + x % 10
                x /= 10
            }
            return _x == y
        }
    }

}