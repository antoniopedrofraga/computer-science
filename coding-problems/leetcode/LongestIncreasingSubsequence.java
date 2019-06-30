import java.util.Arrays;

/**
 * Find the longest increasing subsequence in O(n log n)
 * Memory complexity: O(1)
 *
 * Reference: https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] active = new int[nums.length];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (size == 0 || (size > 0 && active[0] > n)) {
                active[size++] = n;
            } else if (size > 0 && active[size - 1] < n) {
                active[size++] = n;
            } else {
                int index = Arrays.binarySearch(active, 0, size, n);
                if (index < 0) {
                    index = -index - 1;
                }
                active[index] = n;
            }
        }
        return size;
    }

    public void test() {
        /*System.out.println(lengthOfLIS(new int[]{3, 10, 2, 1, 20}));
        System.out.println(lengthOfLIS(new int[]{3, 2}));*/
        System.out.println(lengthOfLIS(new int[]{2, 5, 3, 7, 11, 8, 10, 13, 6}));
    }
}
