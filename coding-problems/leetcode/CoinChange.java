import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Time complexity: O(n k)
 * Memory complexity: O(k)
 */

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int [] dp = new int[amount + 1];
        Arrays.fill(dp, 1, dp.length, Integer.MAX_VALUE);
        for (int c : coins) {
            dp[c] = 1;
        }
        for (int i = 1; i <= amount; i += 1) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            for (int c : coins) {
                if (i + c <= amount) {
                    dp[i + c] = Math.min(dp[i + c], dp[i] + 1);
                }
            }
        }
        return dp[amount];
    }
    public void test() {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }
}
