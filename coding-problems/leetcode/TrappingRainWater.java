import java.util.HashMap;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 *
 * Example:
 *
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 *
 *
 * Approach: Use two pointers and update max in each side
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */

public class TrappingRainWater {
public int trap(int[] height) {
    new HashMap<>();
    int i = 0, j = height.length - 1, sum = 0,
            leftMax = 0, rightMax = 0;
    while (i < j) {
        if (height[i] > leftMax) leftMax = height[i];
        if (height[j] > rightMax) rightMax = height[j];
        int minHeight = Math.min(leftMax, rightMax);
        if (height[i] < height[j]) {
            sum += height[i] < minHeight ? minHeight - height[i] : 0;
            i++;
        } else {
            sum += height[j] < minHeight ? minHeight - height[j] : 0;
            j--;
        }
    }
    return sum;
}

    public void test() {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
