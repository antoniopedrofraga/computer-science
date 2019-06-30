import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 *
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Approach: Use a balanced BST to keep track of the elements
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 *
 * Note: Java doesn't have a valid BST to keep track of the size. I'll implement the quadratic solution.
 */
public class CountSmaller {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int counter = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    counter++;
                }
            }
            result.add(0, counter);
        }
        return result;
    }

}
