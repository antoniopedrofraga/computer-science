import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 *
 * Note: You may assume the string contain only lowercase letters.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class FirstUniqueChar {
    public int firstUniqChar(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        s.chars().forEach(cval -> {
            char c = (char) cval;
            if (seen.containsKey(c)) {
                int index = seen.get(c);
                seen.put(c, index + 1);
            } else {
                seen.put(c, 1);
            }
        });
        for (int i = 0; i < s.length(); i++) {
            Character key = s.charAt(i);
            if (seen.get(key) == 1) return i;
        }
        return -1;
    }

    public void test() {
        System.out.println(firstUniqChar("test"));
    }
}
