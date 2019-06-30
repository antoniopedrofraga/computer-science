/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Input: "12"
 * Output: [1, 2]
 *
 * Input: "226"
 * Output: [1, 2, 3]
 *
 * Approach: Save last two results in order to sum the number of different possibilities.
 * Time complexity: O(n)
 * Memory Complexity: O(1)
 */

public class DecodeWays {
    public int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        int n1 = 1, n2 = 1;
        for (int i = 1; i < s.length(); i++) {
            int curr = 0;
            int v1 = Character.getNumericValue(s.charAt(i));
            int v2 = Character.getNumericValue(s.charAt(i - 1)) * 10 +
                    Character.getNumericValue(s.charAt(i));
            if (v1 > 0 && v1 < 10) curr += n1;
            if (v2 >= 10 && v2 <= 26) curr += n2;
            n2 = n1;
            n1 = curr;
        }
        return n1;
    }
}
