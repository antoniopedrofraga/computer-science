/*
    Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

    Note: The solution set must not contain duplicate subsets.

    Example:
    Input: [1,2,2]
    Output:
    [
    [2],
    [1],
    [1,2,2],
    [2,2],
    [1,2],
    []
    ]

    Time complexity: O(2^n)
    Memory complexity: O(2^n)

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void dfs(int [] nums, int index, List<List<Integer>> result, List<Integer> current) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (current.size() == 0 || current.get(current.size() - 1) != nums[index]) {
            dfs(nums, index + 1, result, current);
        }
        current.add(nums[index]);
        dfs(nums, index + 1, result, current);
        current.remove(current.size() - 1);
    }
}
