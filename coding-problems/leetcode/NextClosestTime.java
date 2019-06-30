import java.util.Set;
import java.util.TreeSet;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
 *
 * Example 1:
 *
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 * Example 2:
 *
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
 *
 * Time complexity: O(n log n) (constant if we assume that the string size is constant)
 * Memory complexity: O(n) (constant if we assume that the string size is constant)
 */

public class NextClosestTime {
    public boolean canUpdate(StringBuilder sb, int i, Character digit) {
        if (digit == null) return false;
        char temp = sb.charAt(i);
        sb.setCharAt(i, digit);
        String hour = sb.substring(0, 2);
        String minutes = sb.substring(3, 5);
        if (Integer.parseInt(hour) < 24 && Integer.parseInt(minutes) < 60) {
            return true;
        }
        sb.setCharAt(i, temp);
        return false;
    }
    public String nextClosestTime(String time) {
        TreeSet<Character> bst = new TreeSet<>();
        StringBuilder builder = new StringBuilder(time);
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) == ':')
                continue;
            bst.add(time.charAt(i));
        }
        int i = time.length() - 1;
        for (; i >= 0; i--) {
            if (time.charAt(i) == ':')
                continue;
            Character higher = bst.higher(time.charAt(i));
            if (canUpdate(builder, i, higher)) {
                break;
            }
        }
        Character lowest = bst.pollFirst();
        i++;
        for (; i < builder.length(); i++) {
            if (builder.charAt(i) == ':') continue;
            builder.setCharAt(i, lowest);
        }
        return new String(builder);
    }

    public void test() {
        System.out.println(nextClosestTime("19:34"));
        System.out.println(nextClosestTime("23:59"));
        System.out.println(nextClosestTime("13:55"));
    }
}
