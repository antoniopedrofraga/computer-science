/**
 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.
 *
 * Example 1:
 * Input: [4, 1, 8, 7]
 * Output: True
 * Explanation: (8-4) * (7-1) = 24
 * Example 2:
 * Input: [1, 2, 1, 2]
 * Output: False
 * Note:
 * The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
 * Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
 * You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
 *
 *
 * Approach: Run dfs with backtracking
 * Time complexity: O(1) (n is bounded by 4)
 * Memory complexity: O(1) (n is bounded by 4)
 */
public class Game24 {
public boolean judgePoint24(int[] nums) {
    boolean [] visited = new boolean[]{false, false, false, false};
    for (int i = 0; i < nums.length; i++) {
        visited[i] = true;
        if (recur(nums, visited, nums[i], 0, 1)) return true;
        visited[i] = false;
    }
    return false;
}
public boolean equals24(double value) {
    return 24 - Math.pow(10, -5) <= value && 24 + Math.pow(10, -5) >= value;
}
public boolean recur(int [] nums, boolean [] visited, double result, double accumulated, int depth) {
    if (depth == 4) {
        return equals24(result * accumulated ) ||
                equals24(result / accumulated ) || equals24(accumulated / result) ||
                equals24(result + accumulated) ||
                equals24(result - accumulated);
    }
    for (int i = 0; i < nums.length; i++) {
        if (visited[i]) continue;
        visited[i] = true;
        if (recur(nums, visited, result + accumulated, nums[i], depth + 1) ||
            (depth != 1 && recur(nums, visited, result - accumulated, nums[i], depth + 1)) ||
            (depth != 1 && recur(nums, visited, result * accumulated, nums[i], depth + 1)) ||
            (depth != 1 && accumulated != 0 && recur(nums, visited, result / accumulated, nums[i], depth + 1)))
        {
            return true;
        }
        if ((depth != 1 && recur(nums, visited, result, accumulated + nums[i], depth + 1)) ||
            (depth != 1 && recur(nums, visited, result, accumulated - nums[i], depth + 1)) ||
            (depth != 1 && recur(nums, visited, result, accumulated * nums[i], depth + 1)) ||
            (depth != 1 && recur(nums, visited, result, accumulated / nums[i], depth + 1))) {
            return true;
        }
        visited[i] = false;
    }
    return false;
}

    public void test() {
        System.out.println(judgePoint24(new int[]{4, 1, 8, 7}));
        System.out.println(judgePoint24(new int[]{1, 2, 1, 2}));
        System.out.println(judgePoint24(new int[]{3, 4, 6, 7}));
        System.out.println(judgePoint24(new int[]{3, 3, 8, 8}));
    }
}
