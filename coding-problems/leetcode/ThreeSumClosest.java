import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * First approach: Cubic time, three for loops
 * Second approach: Quadratic time, sort, and apply two pointer strategy.
 *
 * Both strategies are O(1) space.
 */

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int closest = 0;
        boolean defined = false;
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            int i = k + 1,  j = nums.length - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (!defined || Math.abs(target - sum) < Math.abs(target - closest)) {
                    defined = true;
                    closest = sum;
                }
                if (sum < target) i++;
                else j--;
            }
        }
        return closest;
    }
}
