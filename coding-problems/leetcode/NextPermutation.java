import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * Approach:
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */

public class NextPermutation {
    void nextPermutation(int[] nums) {
        int j = nums.length - 2;
        while (j >= 0) {
            if (nums[j] < nums[j + 1]) break;
            j--;
        }
        if (j == -1) {
            reverse(nums, 0 , nums.length - 1);
        } else {
            swapAndAdjust(nums, j);
        }
    }

    private void swapAndAdjust(int[] nums, int j) {
        int min = Integer.MAX_VALUE, i = j + 1, k = nums.length;
        for (; i < nums.length; i++) {
            if (nums[i] > nums[j] && nums[i] <= min) {
                min = nums[i];
                k = i;
            }
        }
        if (k != nums.length) {
            int temp = nums[j];
            nums[j] = nums[k];
            nums[k] = temp;
            reverse(nums, j + 1, nums.length - 1);
        }
    }

    private void reverse(int [] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public void test() {
        int [] test1 = new int[]{1,2,3}, test2 = new int[]{3,2,1}, test3 = new int[]{1,1,5}, test4 = new int[]{1,3,2};
        nextPermutation(test1);
        System.out.println(Arrays.toString(test1));
        nextPermutation(test2);
        System.out.println(Arrays.toString(test2));
        nextPermutation(test3);
        System.out.println(Arrays.toString(test3));
        nextPermutation(test4);
        System.out.println(Arrays.toString(test4));
    }
}
