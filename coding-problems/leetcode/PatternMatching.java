/**
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
 *
 *
 *
 * Rules for a valid pattern:
 *
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 * The order of keys used matters.
 *
 * Since our pattern only has 9 different points, we can save each state in a 32 bits integer mask.
 * Bit 0, would hold wether the first square was already visited or not (and so on). We can run a DFS using backtracking, saving the count on each state.
 *
 * A state is defined by the cell we are sliding from and the current bit mask.
 * Since our maximum bit mask takes a value of 512 (2^9), we can save all states in an array dp[3][3][512].
 * A state of dp[y][x][mask] would mean that we are sliding from (x, y) and the current cells (in the mask) are visited.
 *
 * Time complexity: O(n!)
 * Memory complexity: O(n) (the longest pattern)
 */
public class PatternMatching {
    int markVisited(int mask, int y, int x) {
        int bit = 1 << (y * 3 + x);
        if ((mask & bit) > 0) {
            return mask - bit;
        } else {
            return mask + bit;
        }
    }

    boolean isVisited(int mask, int y, int x) {
        int bit = 1 << (y * 3 + x);
        return (mask & bit) > 0;
    }

    boolean isValidMove(int y, int x, int i, int j, int mask) {
        if (y != i && x != j && Math.abs(y - i) != Math.abs(x - j)) return true;
        if (y == i) {
            for (int k = Math.min(x, j) + 1; k < Math.max(x, j); k++) {
                if (!isVisited(mask, y, k)) return false;
            }
        } else if (x == j) {
            for (int l = Math.min(y, i) + 1; l < Math.max(y, i); l++) {
                if (!isVisited(mask, l, x)) return false;
            }
        } else {
            if (Math.abs(x - j) > 1 && !isVisited(mask, 1, 1)) {
                return false;
            }
        }
        return true;
    }

    public int numberOfPatterns(int m, int n) {
        int [][] dp = new int[9][512];
        int count = 0, mask = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                mask = markVisited(mask, y, x);
                count += backtrack(dp, y, x, mask, m, n, 1);
                mask = markVisited(mask, y, x);
            }
        }
        return count;
    }
    int backtrack(int [][] dp, int y, int x, int mask, int m, int n, int depth) {
        if (dp[y * 3 + x][mask] != 0) return dp[y * 3 + x][mask];
        if (depth == n) return 1;
        int count = depth >= m ? 1 : 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isVisited(mask, i, j) && isValidMove(y, x, i, j, mask)) {
                    mask = markVisited(mask, i, j);
                    count += backtrack(dp, i, j, mask, m, n, depth + 1);
                    mask = markVisited(mask, i, j);
                }
            }
        }
        dp[y * 3 + x][mask] = count;
        return count;
    }
    public void test() {
        System.out.println(numberOfPatterns(1, 2));
    }
}
