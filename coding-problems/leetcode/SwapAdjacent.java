import java.util.*;

/**
 * In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.
 *
 * Example:
 *
 * Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * Output: True
 * Explanation:
 * We can transform start to end following these steps:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 * Note:
 *
 * 1 <= len(start) = len(end) <= 10000.
 * Both start and end will only consist of characters in {'L', 'R', 'X'}.
 *
 * Approach: Use two pointers for each one of the strings and parse your result from there.
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */
public class SwapAdjacent {
    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) return false;
        int j = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != 'X') {
                for (; j < end.length(); j++) {
                    if (end.charAt(j) != 'X') break;
                }
                if (j >= end.length() || start.charAt(i) != end.charAt(j)) return false;
                if (start.charAt(i) == 'L' && i < j) return false;
                if (start.charAt(i) == 'R' && j < i) return false;
                j++;
            }
        }
        for (; j < end.length(); j++) {
            if (end.charAt(j) != 'X') return false;
        }
        return true;
    }


    public void test() {
        List<String> starts = Arrays.asList("RXXLRXRXL", "XXXXXLXXXX", "XXRXXLXXXX", "XRXXXLXXXR");
        List<String> ends = Arrays.asList("XRLXXRRLX", "LXXXXXXXXX", "XXXXRXXLXX", "XXRLXXXRXX");
        List<Boolean> expected = Arrays.asList(true, true, false, false);
        for (int i = 0; i < starts.size(); i++) {
            System.out.println("\"" + starts.get(i) + "\" to \"" + ends.get(i) + "\" result(" + canTransform(starts.get(i), ends.get(i)) + ") expected(" + expected.get(i) + ")");
        }
    }
}
