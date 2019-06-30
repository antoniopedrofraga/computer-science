import java.util.*;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * Example:
 *
 * Input:  n = 2
 * Output: ["11","69","88","96"]
 *
 *
 * Approach: Use backtracking to generate all possibilities.
 * Time complexity: O(5^n)
 * Memory complexity: O(5^n)
 */
class StrobogrammaticNumberII {
    static final List<String> pairs = Arrays.asList("00", "11", "69", "88", "96");
    static final List<Character> middle = Arrays.asList('0', '1', '8');

    List<String> results;
    public List<String> findStrobogrammatic(int n) {
        results = new LinkedList<>();
        char [] builder = new char[n];
        recur(builder, 0, n);
        return results;
    }
    private void recur(char [] builder, int i, int n) {
        int j = n - 1 - i;
        if (i == j) {
            for (char c : middle) {
                builder[i] = c;
                results.add(new String(builder));
            }
            return;
        } else if (i >= j) {
            results.add(new String(builder));
            return;
        }
        for (String pair : pairs) {
            if (i == 0 && pair.equals("00")) continue;
            builder[i] = pair.charAt(0);
            builder[j] = pair.charAt(1);
            recur(builder, i + 1, n);
        }
    }

    public void test() {
        System.out.println(findStrobogrammatic(1).toString());
        System.out.println(findStrobogrammatic(2).toString());
        System.out.println(findStrobogrammatic(3).toString());
    }
}
