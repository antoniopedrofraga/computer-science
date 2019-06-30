import java.util.Arrays;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 *
 *
 * Approach: Use a boolean array to indicate which positions are bold.
 * Time complexity: O(n^2 * k)
 * Memory complexity: O(n)
 */
public class AddBoldTag {
public String addBoldTag(String s, String[] dict) {
    StringBuilder builder = new StringBuilder();
    boolean [] bold = new boolean[s.length()];
    int nextBold = 0;
    for (int i = 0; i < s.length(); i++) {
        for (String pattern : dict) {
            int length = pattern.length();
            if (s.startsWith(pattern, i)) {
                nextBold = Math.max(nextBold, i + length);
            }
        }
        bold[i] = i < nextBold;
    }
    boolean prevBold = false;
    for (int i = 0; i < s.length(); i++) {
        if (!prevBold && bold[i]) {
            builder.append("<b>");
        } else if (prevBold && !bold[i]) {
            builder.append("</b>");
        }
        builder.append(s.charAt(i));
        prevBold = bold[i];
    }
    if (prevBold) {
        builder.append("</b>");
    }
    return builder.toString();
}

    public void test() {
        System.out.println(addBoldTag("abcxyz123", new String[]{"abc","123"}));
        System.out.println(addBoldTag("aaabbcc", new String[]{"aaa","aab","bc"}));
    }
}
