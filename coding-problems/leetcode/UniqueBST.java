import java.util.Arrays;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 *
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * Approach: Use dynamic programming to save result from i to j.
 * Time complexity: O(n 2)
 * Memory Complexity: O(n 2)
 */
public class UniqueBST {
    public int numTrees(int n) {
        int [][] memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i][i] = 1;
        }
        for (int delta = 1; delta <= n; delta++) {
            for (int j = 0; j <= n - delta; j++) {
                int k = j + delta;
                for (int i = j; i < k; i++) {
                    memo[j][k] += memo[j][i] * memo[i + 1][k];
                }
            }
        }
        return memo[0][n];
    }

}
