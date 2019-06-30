import java.util.LinkedList;
import java.util.List;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */

public class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new LinkedList<>();
        int[] letters = new int[26];
        int start = 0, last = 0;
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            letters[c] = i;
        }
        for (int i = 0; i < S.length(); i++) {
            int c = S.charAt(i) - 'a';
            last = Math.max(last, letters[c]);
            if (last == i) {
                result.add(last - start + 1);
                start = last + 1;
            }
        }
        return result;
    }
}
