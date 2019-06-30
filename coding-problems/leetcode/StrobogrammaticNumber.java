import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 *
 * Input:  "69"
 * Output: true
 * Example 2:
 *
 * Input:  "88"
 * Output: true
 * Example 3:
 *
 * Input:  "962"
 * Output: false
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        int i = 0, j = num.length() - 1;
        while (i <= j) {
            String c = "" + num.charAt(i++) + num.charAt(j--);
            if (!"00 11 88 696".contains(c)) return false;
        }
        return true;
    }
}
