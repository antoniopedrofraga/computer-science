import java.util.Stack;
import java.util.TreeSet;

/**
 * There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day, there will be exactly one flower blooming and it will be in the status of blooming since then.
 *
 * Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.
 *
 * For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.
 *
 * Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers between them is k and these flowers are not blooming.
 *
 * If there isn't such day, output -1.
 *
 * Example 1:
 * Input:
 * flowers: [1,3,2]
 * k: 1
 * Output: 2
 * Explanation: In the second day, the first and the third flower have become blooming.
 * Example 2:
 * Input:
 * flowers: [1,2,3]
 * k: 1
 * Output: -1
 * Note:
 * The given array will be in the range [1, 20000].
 *
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 */
public class KEmptySlots {
public int kEmptySlots(int[] flowers, int k) {
    TreeSet<Integer> bst = new TreeSet<>();
    for (int i = 1; i <= flowers.length; i++) {
        int flower = flowers[i - 1];
        Integer lower = bst.lower(flower);
        Integer higher = bst.higher(flower);
        if (lower != null && flower - lower == k + 1) {
            return i;
        }
        if (higher != null && higher - flower == k + 1) {
            return i;
        }
        bst.add(flower);
    }
    return -1;
}

    public void test() {
        System.out.println(kEmptySlots(new int[]{1,3,2}, 1));
        System.out.println(kEmptySlots(new int[]{1,2,3}, 1));
        System.out.println(kEmptySlots(new int[]{6,5,8,9,7,1,10,2,3,4}, 2));
    }
}
