import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * Approach: O(n^2) with memoization.
 * Time complexity: O(n^2)
 * Memory complexity: O(n)
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean [] memo = new boolean[s.length() + 1];
        Set<String> dict = new HashSet<>(wordDict);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if ((i == 0 || memo[i]) && dict.contains(s.substring(i, j))) {
                    memo[j] = true;
                }
            }
        }
        return memo[s.length()];
    }



}
