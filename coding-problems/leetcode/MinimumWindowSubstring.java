import javafx.util.Pair;
import java.util.*;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

/**
 * Approach: Create a sliding window of valid substrings. Both pointers will only run the string "s" once.
 * Memory complexity: O(n)
 * Time complexity: O(n)
 */
public class MinimumWindowSubstring {
    private Pair<Integer, Integer> incrementRight(Map<Character, Integer> counter,
                             Map<Character, Integer> characters,
                             String s,
                             int rightIndex,
                             int charactersCompleted) {
        while (charactersCompleted < characters.size() && rightIndex < s.length()) {
            Character key = s.charAt(rightIndex);
            if (characters.get(key) == null) {
                rightIndex++;
                continue;
            }
            if (!counter.containsKey(key)) {
                counter.put(key, 1);
            } else {
                int value = counter.get(key) + 1;
                counter.remove(key);
                counter.put(key, value);
            }
            if (characters.get(key).equals(counter.get(key))) {
                charactersCompleted++;
            }
            if (charactersCompleted == characters.size()) {
                return new Pair<>(rightIndex + 1, charactersCompleted);
            }
            rightIndex++;
        }
        return null;
    }
    public String minWindow(String s, String t) {
        int leftIndex = 0, rightIndex = 0,
                minLeft = -1, minRight = Integer.MAX_VALUE - 1, charactersCompleted = 0;
        Map<Character, Integer> counter = new HashMap<>();
        Map<Character, Integer> characters = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character character = t.charAt(i);
            if (!characters.containsKey(character)) {
                characters.put(t.charAt(i), 1);
            } else {
                int number = characters.get(character) + 1;
                characters.remove(character);
                characters.put(t.charAt(i), number);
            }
        }
        Pair<Integer, Integer> result = incrementRight(counter, characters, s, rightIndex, charactersCompleted);
        if (result == null) { return ""; }
        rightIndex = result.getKey();
        charactersCompleted = result.getValue();
        if (charactersCompleted == characters.size()) {
            while (leftIndex < s.length()) {
                Character key = s.charAt(leftIndex);
                if (charactersCompleted == characters.size() && Math.abs(rightIndex - leftIndex) < Math.abs(minRight - minLeft) && leftIndex < rightIndex) {
                    minLeft = leftIndex;
                    minRight = rightIndex;
                }
                if (characters.containsKey(key) && counter.containsKey(key) && characters.get(key).intValue() == counter.get(key).intValue()) {
                    charactersCompleted--;
                }
                if (counter.containsKey(key)) {
                    int value = counter.get(key) - 1;
                    counter.remove(key);
                    counter.put(key, value);
                }
                leftIndex++;
                if ((charactersCompleted < characters.size() || rightIndex <= leftIndex) && rightIndex < s.length()) {
                    result = incrementRight(counter, characters, s, rightIndex, charactersCompleted);
                    if (result != null) {
                        rightIndex = result.getKey();
                        charactersCompleted = result.getValue();
                    }
                }
            }
        }
        return s.substring(minLeft, minRight);
    }
}
