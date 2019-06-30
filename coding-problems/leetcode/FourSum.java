import java.util.*;

/**
 *
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * Time complexity: O(n cube)
 * Memory complexity: O(n)
 */
public class FourSum {
    class Node {
        List<Integer> list;

        public Node(List<Integer> list) {
            this.list = list;
            Collections.sort(this.list);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.list);
        }

        @Override
        public boolean equals(Object obj) {
            List<Integer> list2 = ((Node) obj).list;
            for (int i = 0; i < 4; i++) {
                if (!list.get(i).equals(list2.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Map<Integer, Integer> dict = new HashMap<>();
        Set<Node> included = new HashSet<>();
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            dict.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (target - sum > nums[nums.length - 1]) break;
                    if (target - sum < nums[0]) continue;
                    Integer l = dict.get(target - sum);
                    if (l != null && l != j && l != i && l != k) {
                        List<Integer> numbers = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        Node node = new Node(numbers);
                        if (!included.contains(node)) {
                            included.add(node);
                            result.add(numbers);
                        }
                    }
                }
            }
        }
        return result;
    }


    public void test() {
        System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2} ,0).toString());
        System.out.println(fourSum(new int[]{-3,-2,-1,0,0,1,2,3} ,0).toString());
    }
}
