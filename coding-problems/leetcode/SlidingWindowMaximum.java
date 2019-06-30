import java.util.*;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindowPq(int[] nums, int k) {
        if (k == 0) return new int[]{};
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] result = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }
        result[j++] = pq.peek();
        for (int i = k; i < nums.length; i++) {
            pq.remove(nums[i - k]);
            pq.add(nums[i]);
            result[j++] = pq.peek();
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[] {};
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (i >= k - 1) {
                result[j++] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    public void test() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
    }
}
