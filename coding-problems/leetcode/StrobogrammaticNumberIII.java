import java.util.ArrayList;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * Example:
 *
 * Input: low = "50", high = "100"
 * Output: 3
 * Explanation: 69, 88, and 96 are three strobogrammatic numbers.
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */

@SuppressWarnings("unchecked")
public class StrobogrammaticNumberIII {
    List<String> strobs = new ArrayList() {{
        add("00");
        add("11");
        add("69");
        add("88");
        add("96");
    }};
    List<String> single = new ArrayList() {{
        add("0");
        add("1");
        add("8");
    }};

    public int ways(int length) {
        int sum = (int) Math.pow(strobs.size(), length / 2 - 1);
        if (length % 2 == 1) sum *= single.size();
        return sum;
    }

    public int waysWrapper(int length) {
        if (length == 1) return single.size();
        return 4 * ways(length);
    }

    public int invalid(String s, StringBuilder builder, int i, int j, boolean less) {
        if (i > j) {
            if (less && builder.toString().compareTo(s) < 0) {
                return 1;
            }
            if (!less && builder.toString().compareTo(s) > 0) {
                return 1;
            }
            return 0;
        }
        int sum = 0;
        List<String> list = i == j ? single : strobs;
        for (String strob : list) {
            if (i == 0 && builder.length() != 1 && strob.charAt(0) == '0') continue;
            builder.setCharAt(i, strob.charAt(0));
            if (i < j) {
                builder.setCharAt(j, strob.charAt(1));
            }
            sum += invalid(s, builder, i + 1, j - 1, less);
        }
        return sum;
    }
    public int strobogrammaticInRange(String low, String high) {
        int sum = 0, lt = 0, gt = 0;
        StringBuilder builder = new StringBuilder(high.length());
        for (int i = low.length(); i <= high.length(); i++) {
            sum += waysWrapper(i);
            if (i == low.length())  {
                builder.setLength(low.length());
                lt = invalid(low, builder, 0, low.length() - 1, true);
            }
            if (i == high.length())  {
                builder.setLength(high.length());
                gt = invalid(high, builder, 0, high.length() - 1, false);
            }
        }
        return sum - lt - gt;
    }

    public void test() {
        System.out.println(strobogrammaticInRange("50", "100"));
        System.out.println(strobogrammaticInRange("0", "0"));
        System.out.println(strobogrammaticInRange("8", "8"));
        System.out.println(strobogrammaticInRange("1", "1"));
        System.out.println(strobogrammaticInRange("0", "9"));
        System.out.println(strobogrammaticInRange("100", "1000"));
        System.out.println(strobogrammaticInRange("1", "0"));
        System.out.println(strobogrammaticInRange("11", "69"));
        System.out.println(strobogrammaticInRange("0", "1680"));
    }
}
