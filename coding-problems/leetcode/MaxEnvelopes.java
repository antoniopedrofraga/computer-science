import java.util.Arrays;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 *
 * Approach: We can either solve it with dynamic programming (quadratic) or by using the longest increasing sub-sequence algorithm.
 * If we sort the envelopes first by width (increasing), and then height (decreasing), we can apply the longest increasing sub-sequence algorithm.
 * The only thing to be careful about, would be the height of envelopes with the same width. This case is covered by sorting heights of the same width in decreasing order.
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 */

public class MaxEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e2[0] == e1[0]) {
                return e2[1] - e1[1];
            }
            return e1[0] - e2[0];
        });
        int [] dp = new int[envelopes.length];
        int length = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int h = envelopes[i][1];
            if (length == 0 || dp[0] > h) {
                dp[0] = h;
                if (length == 0) length++;
            } else if (dp[length - 1] < h) {
                dp[length++] = h;
            } else {
                int index = Arrays.binarySearch(dp, 0, length, h);
                if (index < 0) {
                    index = -index - 1;
                }
                dp[index] = h;
            }
        }
        return length;
    }

    public void test() {
        System.out.println(maxEnvelopes(new int[][]{
                {5,4},{6,4},{6,7},{2,3}
        }));
        System.out.println(maxEnvelopes(new int[][]{
                {1,3},{3,5},{6,7},{6,8},{8,4},{9,5}
        }));
    }
}
