import java.util.Arrays;

/**
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Time complexity: O(log n)
 * Memory complexity: O(log n)
 *
 */

public class FirstLastPosSortedArray {
    int find(int [] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    public int[] searchRange(int[] nums, int target) {
        int left = find(nums, target);
        if (left == nums.length || nums[left] != target) return new int[]{-1, -1};
        int right = find(nums, target + 1) - 1;
        return new int[]{left, right};
    }

    public void test() {
        System.out.println(Arrays.toString(searchRange(new int[]{}, 0)));
        System.out.println(Arrays.toString(searchRange(new int[]{1}, 1)));
        System.out.println(Arrays.toString(searchRange(new int[]{1, 3}, 1)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println(Arrays.toString(searchRange(new int[]{5,7,7,8,8,10}, 6)));
    }
}
