import java.util.Arrays;

/**
 * Given an unsorted array nums, reorder it in-place such that
 * nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Example:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: One possible answer is [3,5,1,6,2,4]
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(1)
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i += 2) {
            if (i + 1 < nums.length) {
                int temp = nums[i];
                nums[i] = nums[i + 1];
                nums[i + 1] = temp;
            }
        }
    }

}
