import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int [] result = new int[nums.length];
        int carry = 1;
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            carry *= nums[i -1];
            result[i] = carry;
        }
        carry = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            carry *= nums[i + 1];
            result[i] *= carry;
        }
        return result;
    }


    public void test() {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1,2,3,4})));
    }
}
