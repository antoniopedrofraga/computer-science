import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * Example 1:
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 *
 * Example 2:
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 *
 * Approach: Run a linear algorithm and update min and max as we go.
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if (nums.length == 0) return Collections.emptyList();
        List<String> result = new ArrayList<>();
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 >= nums.length || (long)(nums[i + 1] - nums[i]) > 1) {
                if (min < nums[i]) {
                    result.add(min + "->" + nums[i]);
                } else {
                    result.add(min + "");
                }
                min = i + 1 >= nums.length ? min : nums[i + 1];
            }
        }
        return result;
    }

}
