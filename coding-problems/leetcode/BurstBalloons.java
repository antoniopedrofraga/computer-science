import java.util.Arrays;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * Approach: divide and conquer with memoization. Let the algorithm behave like a stack. The first balloon to be chosen is the last to be bursted.
 * Time complexity: O(n^3)
 * Memory Complexity: O(n^2)
 *
 */
public class BurstBalloons {
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        int [][] memo = new int[nums.length][nums.length];
        Arrays.stream(memo).forEach(arr -> Arrays.fill(arr, Integer.MIN_VALUE));
        return divideAndConquer(memo, nums, 0, nums.length - 1);
    }
    public int divideAndConquer(int [][] memo, int [] nums, int i, int j) {
        if (i > j) return 0;
        if (memo[i][j] != Integer.MIN_VALUE) return memo[i][j];
        int max = 0;
        for (int k = i; k <= j; k++) {
            int value = divideAndConquer(memo, nums, i, k - 1) +
                    (i - 1 < 0 ? 1 : nums[i - 1] ) * nums[k] * (j + 1 >= nums.length ? 1 : nums[j + 1]) +
                    divideAndConquer(memo, nums, k + 1, j);
            max = Math.max(value, max);
        }
        memo[i][j]= max;
        return max;
    }


}
