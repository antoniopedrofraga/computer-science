/**
 * TO-DO: implement this solution with dynamic programming, by using an iterative method, with a matrix.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Regex {
    private static char ANY = '.';
    private static char ZERO_OR_MORE = '*';
    private HashMap<String, HashSet> dp = new HashMap<>();

    private void save(String string, String pattern) {
        if (dp.containsKey(string)) {
            dp.get(string).add(pattern);
        } else {
            dp.put(string, new HashSet(Arrays.asList(pattern)));
        }
    }

    private boolean isSaved(String string, String pattern) {
        if (dp.containsKey(string)) {
            return dp.get(string).contains(pattern);
        }
        return false;
    }

    public boolean isMatch(String string, String pattern) {
        if (isSaved(string, pattern)) {
            return true;
        }

        if (string.length() > 0 && pattern.length() == 0) {
            return false;
        } else if (string.length() == 0 && pattern.length() == 0) {
            return true;
        } else if (string.length() == 0 && pattern.length() > 0) {
            if (pattern.length() == 1) {
                return false;
            } else {
                if (pattern.charAt(1) == ZERO_OR_MORE) {
                    boolean result = isMatch(string, pattern.substring(2));
                    if (result)
                        save(string, pattern);
                    return result;
                } else {
                    return false;
                }
            }
        }

        if (Character.isLetter(string.charAt(string.length() - 1)) &&
        Character.isLetter(pattern.charAt(pattern.length() - 1))) {
            if (string.charAt(string.length() - 1) !=
                    pattern.charAt(pattern.length() - 1))
                return false;
        }

        if (pattern.length() > 1 && pattern.charAt(1) == ZERO_OR_MORE) {
            boolean result = false;
            if (string.length() > 0 && (string.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == ANY)) {
                result = isMatch(string.substring(1), pattern.substring(2)) ||
                        isMatch(string.substring(1), pattern);
            }
            result = isMatch(string, pattern.substring(2)) ||
                    result;

            if (result)
                save(string, pattern);

            return result;
        } else {
            if (string.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == ANY) {
                boolean result = isMatch(string.substring(1), pattern.substring(1));
                if (result)
                    save(string, pattern);

                return result;
            } else {
                return false;
            }
        }
    }

}
