import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 *
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * Approach: We want to know if sum from index i to j is equal to k. We can instead calculate the sum to i, and sum to k we would know that:
 *
 * sum(j) - sum(i) = k, and therefore, sum(i) = k - sum(j)
 * We just want to know the number of ways of getting to sum(j).
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class SubarraySumK {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> dp = new HashMap<>();
        int sum = 0, count = 0;
        dp.put(0, 1);
        for (int i = 0; i < nums.length;  i++) {
            sum += nums[i];
            if (dp.containsKey(sum - k))
                count += dp.get(sum - k);
            dp.put(sum, dp.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
