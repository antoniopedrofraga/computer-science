/**
 * You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.
 *
 * Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.
 *
 * Example 1:
 *
 * Input: [2,-1,1,2,2]
 * Output: true
 * Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
 * Example 2:
 *
 * Input: [-1,2]
 * Output: false
 * Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
 * Example 3:
 *
 * Input: [-2,1,-1,-2,-2]
 * Output: false
 * Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
 *
 * Implement the slow / fast pointer strategy, similar to the linked list strategy.
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */

public class CircularArrayLoop {
    int getIndex(int [] nums, int i) {
        int j = i + nums[i];
        if (j < 0) return nums.length + (j % nums.length);
        else if (j >= nums.length) return j % nums.length;
        else return j;
    }

    public boolean circularArrayLoop(int[] nums) {
        if (nums.length <= 1) return false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            int slow = i, fast = getIndex(nums, i), count = 0;
            boolean forward = nums[i] > 0,
                    backward = nums[i] < 0;
            while (slow != fast) {
                int newFast = getIndex(nums, fast);
                int newSlow = count % 2 == 0 ? slow : getIndex(nums, slow);
                if (!backward && nums[fast] < 0) backward = true;
                if (!forward && nums[fast] > 0) forward = true;
                fast = newFast;
                slow = newSlow;
                count++;
            }
            if (nums[slow] != 0 && forward != backward && fast != getIndex(nums, fast)) {
                return true;
            }
            slow = i;
            while (nums[slow] != 0) {
                nums[slow] = 0;
                slow = getIndex(nums, slow);
            }
        }
        return false;
    }


    public void test() {
        System.out.println(circularArrayLoop(new int[]{2,-1,1,2,2}));
        System.out.println(circularArrayLoop(new int[]{-1,2}));
        System.out.println(circularArrayLoop(new int[]{-2,1,-1,-2,-2}));
    }
}
