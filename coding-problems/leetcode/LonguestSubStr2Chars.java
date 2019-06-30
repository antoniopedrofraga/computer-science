import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 * Approach: keep track of last indices of the two characters, chooses the one that maximizes size.
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */
public class LonguestSubStr2Chars {
public int removeMin(Map<Character, Integer> chars) {
    int min = Integer.MAX_VALUE;
    char toRemove = ' ';
    for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
        int index = entry.getValue();
        if (min > index) {
            min = index;
            toRemove = entry.getKey();
        }
    }
    chars.remove(toRemove);
    return min;
}
public int lengthOfLongestSubstringTwoDistinct(String s) {
    Map<Character, Integer> chars = new TreeMap<>();
    int max = 0, k = 0;
    for (int i = 0; i < s.length(); i++) {
        if (chars.size() == 2 && !chars.containsKey(s.charAt(i))) {
            k = removeMin(chars) + 1;
        }
        chars.put(s.charAt(i), i);
        if (i - k + 1 > max) {
            max = i - k + 1;
        }
    }
    return max;
}

    public void test() {
        List<String> tests = Arrays.asList("eceba", "ccaabbb");
        List<Integer> expected = Arrays.asList(3, 5);
        for (int i = 0; i < tests.size(); i++) {
            System.out.println("The longuest substring with 2 chars in \"" + tests.get(i) + "\" is " + expected.get(i) + " and got " + lengthOfLongestSubstringTwoDistinct(tests.get(i)));
        }
    }

}
