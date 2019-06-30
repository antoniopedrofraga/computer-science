import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 * Approach: keep track of fruits collected until the i-th position, if we can not add a fruit, then we should delete all fruits to the left of that fruit and calculate the maximum number.
 *
 * Approach: Check what is the fruit (in the basket) whose the largest index is the smallest. Remove it from the basket.
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 *
 */
public class FruitsIntoBaskets {
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> basket = new TreeMap<>();
        int k = 2, left = 0, max = 0;
        for (int i = 0; i < tree.length; i++) {
            if (!basket.containsKey(tree[i]) && tree.length >= k) {
                int min = Integer.MAX_VALUE, id = 0;
                for (Map.Entry<Integer, Integer> entry
                        : basket.entrySet()) {
                    if (entry.getValue() < min) {
                        min = entry.getValue();
                        id = entry.getKey();
                    }
                }
                basket.remove(id);
                left = min + 1;
            }
            basket.put(tree[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }


    public void test() {
        System.out.println(totalFruit(new int[]{1,2,1}));
        System.out.println(totalFruit(new int[]{0,1,2,2}));
        System.out.println(totalFruit(new int[]{1,2,3,2,2}));
        System.out.println(totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));
    }
}
