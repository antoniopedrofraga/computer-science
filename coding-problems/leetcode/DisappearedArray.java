import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 *
 * Approach: we can assume that the list to return is not extra space, then we can just build the initial list with all elements set to index, and replace them with -1 in case they are found in the list.
 * Time complexity: O(n)
 * Memory complexity: O(1) (if we don’t count with the list to be returned)
 */
public class DisappearedArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        IntStream.range(1, nums.length + 1).forEach(result::add);
        IntStream.range(0, nums.length).forEach(i -> result.set(nums[i] - 1, 0));
        int offset = 0;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == 0) {
                offset++;
                continue;
            }
            result.set(i - offset, result.get(i));
        }
        return result.subList(0, result.size() - offset);
    }
    public void test() {
        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}).toString());
    }
}
