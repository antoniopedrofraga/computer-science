import java.util.Arrays;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Idea: Make changes in place.
 * Time complexity: O(n) (O(n log n) with a comparison sort)
 * Memory complexity: O(1)
 */

public class RemoveDuplicates {
    public int removeDuplicates(int [] nums) {
        if (nums.length == 0) return 0;

        int consecutive = 0, swapped = 0;
        Arrays.sort(nums);
        int previous = nums[nums.length - 1];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == previous) {
                consecutive++;
                if (consecutive > 2) {
                    int temp = nums[nums.length - 1 - swapped];
                    nums[nums.length - 1 - swapped] = nums[i];
                    nums[i] = temp;
                    swapped++;
                }
            } else {
                consecutive = 1;
                previous = nums[i];
            }
        }
        Arrays.sort(nums, 0, nums.length - swapped);
        return nums.length - swapped;
    }
}
