import java.util.Arrays;
import java.util.List;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Approach: Use dynamic programing to state whether s[i] to p[j] is a valid sequence.
 * Time complexity: O(|s| |p|)
 * Memory complexity: O(|s| |p|)
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        boolean [][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') dp[i][0] = true;
            else break;
        }
        for (int j = 1; j < p.length() + 1; j++) {
            for (int i = 1; i < s.length() + 1; i++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[j][i] = dp[j - 1][i - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[j][i] = dp[j][i - 1] || dp[j - 1][i];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
    public void test() {
        List<String> test = Arrays.asList("aa", "aab", "aa", "aa", "cb", "adceb", "acdcb", "", "", "a", "ho");
        List<String> patterns = Arrays.asList("aa", "c*a*b", "a", "*", "a", "*a*b", "a*c?b", "*", "a", "", "**ho");
        List<Boolean> expected = Arrays.asList(true, false, false, true, false, true, false, true, false, false, true);
        for (int i = 0; i < test.size(); i++) {
            System.out.println("Testing \"" + test.get(i) + "\" against \"" + patterns.get(i) + "\" should be " + expected.get(i) + " - " +
                    (expected.get(i) == isMatch(test.get(i), patterns.get(i)) ? "PASSED" : "FAILED"));
        }
    }
    void print(boolean [][] dp) {
        for (boolean [] arr : dp) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }
}
