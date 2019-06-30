import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> history = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int value = target - nums[i];
            if (history.containsKey(value)) {
                return new int[] {history.get(value), i};
            } else {
                history.put(nums[i], i);
            }
        }
        return new int[] {};
    }
}
