import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 *
 * Approach: We know that,
 *
 * A de Bruijn sequence of order n on a size-k alphabet A is a cyclic sequence in which every possible length-n string on A occurs exactly once as a substring. It has length k^n, which is also the number of distinct substrings of length n on a size-k alphabet; de Bruijn sequences are therefore optimally short.
 *
 * Therefore, we would know that such string would have to be of length k^n.
 *
 * Time complexity: O(k^n)
 * Memory complexity: O(k^n)
 *
 */
public class CrackSafe {
    private int n;
    private int k;
    private int limit;
    private String result = null;

    public String crackSafe(int n, int k) {
        this.n = n;
        this.k = k;
        this.limit = (int) Math.pow(k, n);
        this.result = null;
        dfs(new StringBuilder(), new HashSet<>());
        return result;
    }

    private void dfs(StringBuilder current, Set<String> seen) {
        if (seen.size() == limit && result == null) {
            result = current.toString();
            return;
        }
        if (current.length() > limit * n) return;
        for (int i = 0; i < k; i++) {
            current.append(i + "");
            String permutation = current.length() < n ? null : current.substring(current.length() - n, current.length());
            if (permutation == null || !seen.contains(permutation)) {
                if (permutation != null) seen.add(permutation);
                dfs(current, seen);
                if (permutation != null) seen.remove(permutation);
            }
            if (result != null) break;
            current.setLength(current.length() - 1);
        }
    }

    public void test() {
        List<Integer> ns = Arrays.asList(1, 2, 1);
        List<Integer> ks = Arrays.asList(2, 2, 10);
        List<Integer> expectedSize = Arrays.asList(2, 5, 10);
        for (int i = 0; i < ns.size(); i++) {
            String result = crackSafe(ns.get(i), ks.get(i));
            if (result.length() != expectedSize.get(i)) {
                System.out.println(" n = " + ns.get(i) + " k = " + ks.get(i) + " produced " + crackSafe(ns.get(i), ks.get(i)) + " but expected a string of length " + expectedSize.get(i));
            }
        }
    }
}
