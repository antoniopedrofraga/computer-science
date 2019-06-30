import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 *
 * Example:
 *
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 *
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 *
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 *
 *
 * This technique is called reservoir sampling. If we have 3 elements with the same value, the probability of choosing 1 of them is (1 * 1 / 2 * 2 / 3 = 1 / 3). Therefore, we can conclude our formula as
 *
 * (1 / i) * ( 1 - 1 / (i + 1)) * ( 1 - 1 / (i + 2))
 *
 * TIme complexity: O(n)
 * Memory complexity: O(1)
 */
public class RandomPickIndex {
    int [] nums;
    Random random;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }

    public int pick(int target) {
        int count = 0, picked = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            if (random.nextInt(++count) == 0) {
                picked = i;
            }
        }
        return picked;
    }

}
