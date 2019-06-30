import java.util.ArrayList;
import java.util.List;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Approach: Create an aux array to keep track of the minimum number of moves at index i. Use dynamic programming to update min number of moves at each index from 1 … n.
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class PerfectSquares {

    public int numSquares(int n) {
        int [] minTries = new int[n + 1];
        List<Integer> perfectSquares = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            double square = Math.sqrt(i);
            if ((long) square == square) {
                perfectSquares.add(i);
                minTries[i] = 1;
                if (i == n) return 1;
            } else {
                minTries[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (minTries[i] > 0) {
                for (int square : perfectSquares) {
                    if (i + square <= n) {
                        minTries[i + square] = Math.min(minTries[i] + 1, minTries[i + square]);
                    }
                }
            }
        }
        return minTries[n];
    }

}
