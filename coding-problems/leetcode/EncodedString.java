/**
 * Given a non-empty string, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 *
 * Note:
 *
 * k will be a positive integer and encoded string will not be empty or have extra space.
 * You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 *
 *
 * Approach: Use dynamic programming to save the shortest string from i to j.
 * Recognize patterns in case we can see them.
 * Notice that we can formulate our recurrence as being dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j]).
 *
 * Memory complexity: O(n^3)
 * Time complexity: O(n^4)
 */
public class EncodedString {
    public String encode(String s) {
        if (s.length() < 3) return s;
        String [][] dp = new String[s.length()][s.length() + 1];
        for (int j = 1; j <= s.length(); j++) {
            for (int i = j - 1; i >= 0; i--) {
                String subStr = s.substring(i, j);
                dp[i][j] = subStr;
                if (j - i < 3) {
                    continue;
                }
                for (int k = i + 1; k < j; k++) {
                    if (dp[i][k].length() + dp[k][j].length() <
                            dp[i][j].length()) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
                for (int k = 1; k <= subStr.length(); k++) {
                    if (subStr.length() % k != 0) continue;
                    String repeated = subStr.substring(0, k);
                    if (subStr.replaceAll(repeated, "").length() == 0) {
                        String newStr = subStr.length() / repeated.length() + "[" + dp[i][i + k] + "]";
                        if (newStr.length() < dp[i][j].length()) {
                            dp[i][j] = newStr;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][s.length()];
    }
    public void test() {
        System.out.println(encode("aaa"));
        System.out.println(encode("aaaaa"));
        System.out.println(encode("aaaaaaaaaa"));
        System.out.println(encode("aabcaabcd"));
        System.out.println(encode("abbbabbbcabbbabbbc"));
    }
}
