/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example:
 *
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Approach: use dynamic programming to get the max profit.
 *
 * Instantiate two arrays:
 *
 * buy - max profit when buying before i.
 * sell - max profit when selling before i.
 * note: We can reduce the space complexity to O(1)
 */
public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int [] buy = new int[prices.length + 1];
        int [] sell = new int[prices.length + 1];
        buy[0] = Integer.MIN_VALUE; buy[1] = -prices[0];
        for (int i = 2; i <= prices.length; i++) {
            int j = i - 1;
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[j]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[j]);
        }
        return sell[prices.length];
    }
}
