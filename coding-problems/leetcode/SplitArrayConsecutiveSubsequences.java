/**
 * You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.
 *
 * Example 1:
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * Example 2:
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * Example 3:
 * Input: [1,2,3,4,4,5]
 * Output: False
 */

public class SplitArrayConsecutiveSubsequences {
    public boolean isPossible(int[] nums) {
        int [] seq = new int[nums.length];
        int lastCount = 0;
        for (int i = 0; i < nums.length;) {
            int count = 1, num = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (num == nums[j]) count++;
                else break;
            }
            for (int j = i - lastCount; j < i - count; j++) {
                if (seq[j] < 3) return false;
            }
            int min = Math.min(lastCount, count);
            for (int j = i; j < i + min; j++) {
                if (nums[j] == nums[j - min] + 1) {
                    seq[j] = seq[j - min] + 1;
                } else if (seq[j - min] < 3) {
                    return false;
                } else {
                    seq[j] = 1;
                }
            }
            for (int j = i + min; j < i + count; j++) {
                seq[j] = 1;
            }
            lastCount = count;
            i += count;
        }
        for (int j = seq.length - lastCount; j < seq.length; j++) {
            if (seq[j] < 3) return false;
        }
        return true;
    }

    public void test() {
        System.out.println(isPossible(new int[]{3,4,4,5,6,7,8,9,10,11}));
        System.out.println(isPossible(new int[]{1,2,3,3,4,5}));
        System.out.println(isPossible(new int[]{1,2,3,3,4,4,5,5}));
        System.out.println(isPossible(new int[]{1,2,3,4,4,5}));
    }
}
