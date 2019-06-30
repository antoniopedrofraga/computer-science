import java.util.Arrays;
import java.util.List;

/**
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string.
 * If there are more than one possible results, return the longest word with the smallest lexicographical order.
 * If there is no possible result, return the empty string.
 *
 * Time complexity: O(|s| . |d|)
 * Memory complexity: O(|s|)
 */

public class LongestWordDict {

    private boolean isSubSequence(String s, String ss) {
        if (s.length() < ss.length()) return false;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i < j - ss.length()) break;
            if (ss.charAt(j) == s.charAt(i)) j++;
            if (j == ss.length()) break;
        }
        return j == ss.length();
    }

    private String findLongestWord(String s, List<String> d) {
        String max = "";
        for (String string : d) {
            if (string.length() > max.length() && isSubSequence(s, string)) {
                max = string;
            } else if (string.length() == max.length() &&
                    string.compareTo(max) < 0 && isSubSequence(s, string)) {
                max = string;
            }
        }
        return max;
    }

    public void test() {
        System.out.println(findLongestWord(
                "abpcplea", Arrays.asList("ale","apple","monkey","plea")
        ));
        System.out.println(findLongestWord(
                "abpcplea", Arrays.asList("a","b","c")
        ));
    }
}
