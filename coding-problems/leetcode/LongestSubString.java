import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */

public class LongestSubString {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> history = new HashMap<>();
        int maxCount = 0, lowerIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (history.containsKey(s.charAt(i)) && lowerIndex < history.get(s.charAt(i)) + 1) {
                lowerIndex = history.get(s.charAt(i)) + 1;
            }
            if (maxCount < (i - lowerIndex) + 1) {
                maxCount = (i - lowerIndex) + 1;
            }
            history.put(s.charAt(i), i);
        }
        return maxCount;
    }

    public int lengthOfLongestSubstringII(String s) {
        int[] array = new int[128];
        int max = 0, low = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            low = Math.max(low, array[c]);
            array[c] = i + 1;
            max = Math.max(max , i - low + 1);
        }

        return max;
    }

    public void test() {
        System.out.println(lengthOfLongestSubstringII("pwwkew"));
    }
}
