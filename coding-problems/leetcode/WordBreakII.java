import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 *
 * Approach: Use dynamic programming to save list of each position j, i
 * Time complexity: O(n^3)
 * Memory complexity: O(n^3)
 */

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> [] memo = new LinkedList[s.length() + 1];
        for (int i = 0; i <= s.length(); i++) { memo[i] = new LinkedList<>(); }
        Set<String> dict = new HashSet<>(wordDict);

        for (int j = 1; j <= s.length(); j++) {
            List<String> current = new LinkedList<>();
            for (int i = 0; i < j; i++) {
                if (i == 0 && dict.contains(s.substring(i, j))) {
                    current.add(s.substring(i, j));
                } else if (!memo[i].isEmpty() && dict.contains(s.substring(i, j))) {
                    for (String str : memo[i]) {
                        String newStr = str + " " + s.substring(i, j);
                        current.add(newStr);
                    }
                }
            }
            memo[j].addAll(current);
        }
        return memo[s.length()];
    }


}
