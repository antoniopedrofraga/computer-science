/**
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 *
 * Approach: Use memoization + dfs
 * Memory complexity: O(mn)
 * Time complexity: O(mn)
 */

public class LongestIncreasingPath {
    static final int [][] moves = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix.length, m = matrix[0].length, max = 0;
        int [] memo = new int[m * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, dfs(memo, matrix, n, m, i, j));
            }
        }
        return max;
    }

    public int dfs(int [] memo, int [][] matrix, int n, int m, int i, int j) {
        int index = i * m + j;
        if (memo[index] != 0) {
            return memo[index];
        }
        int max = 0;
        for (int [] move : moves) {
            int y = i + move[1], x = j + move[0];
            if (y >= 0 && y < n && x >= 0 && x < m && matrix[y][x] > matrix[i][j]) {
                int local = dfs(memo, matrix, n, m, y, x);
                if (local > max) {
                    max = local;
                }
            }
        }
        memo[index] = max + 1;
        return max + 1;
    }
    public void test() {
        System.out.println(
                longestIncreasingPath(new int[][] {
                        {9,9,4},
                        {6,6,8},
                        {2,1,1}
                })
        );
        System.out.println(
                longestIncreasingPath(new int[][] {
                        {3,4,5},
                        {3,2,6},
                        {2,2,1}
                })
        );
    }
}
