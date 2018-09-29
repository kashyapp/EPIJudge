package epi.kashyap

fun buySellStock(prices: List<Double>): Double {
    var max = 0.0
    var profit = 0.0
    for (p in prices.asReversed()) {
        if (p > max) {
            max = p
        }
        if (max - p > profit) {
            profit = max - p
        }
    }
    return profit
}