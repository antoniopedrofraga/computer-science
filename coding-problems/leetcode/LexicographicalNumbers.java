import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return 1 - n in lexicographical order.
 *
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 *
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 *
 * Approach: We can do some math to know where to place each number.
 * We can also run dfs in order to place elements in the correct order.
 *
 * Time complexity: O(n)
 * Memory complexity: O(1) -> integers are bounded by a constant number of digits
 */

public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 0; i < n; i++) {
            list.add(curr);
            if (curr * 10 <= n) { // 1, 10, 100 ..
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) { // 101, 102, 103 ...
                curr++;
            } else {
                while (curr % 10 == 9 || curr == n) { // 109 -> 11
                    curr /= 10;
                }
                curr += 1;
            }
        }
        return list;
    }
    public List<Integer> lexicalOrderDfs(int n) {
        List<Integer> list = new ArrayList<>(n);
        dfs(list, 1, n);
        return list;
    }
    public void dfs(List<Integer> list, int i, int n) {
        if (i > n) return;
        list.add(i);
        dfs(list, i * 10, n);
        if (i % 10 != 9) {
            dfs(list, i + 1, n);
        }
    }
}
