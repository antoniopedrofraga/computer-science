import java.util.*;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 * Time complexity: There are n * (n + 1) / 2 sub arrays, which we can try for each n. So we have O(n^2)
 * Memory complexity: O(n^2) but we can easily reduce it to O(n).
 *
 */

public class SplitArray {
    public int splitArray(int[] nums, int m) {
        int [][] memo = new int[nums.length][nums.length];
        return recurSubArrays(nums, memo, m - 1, 0);
    }
    int recurSubArrays(int[] nums, int [][] memo, int m, int i) {
        int j = memo.length - m;
        if (memo[i][m] != 0) return memo[i][m];
        int min = Integer.MAX_VALUE, sum = 0;
        for (int k = i; k < j; k++) {
            sum += nums[k];
            int value = m > 0 && sum < min ? recurSubArrays(nums, memo, m - 1, k + 1) : 0;
            if ((m == 0 && k + 1 == j) || m > 0) {
                min = Math.min(min, Math.max(sum, value));
            }
        }
        memo[i][m] = min;
        return min;
    }

    public void test() {
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));
    }
}
