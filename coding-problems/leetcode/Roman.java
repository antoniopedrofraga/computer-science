import java.util.Arrays;

public class Roman {
    int [] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String [] symbol = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",  "V", "IV", "I" };

    public String intToRoman(int num) {
        int i = 0;
        StringBuilder result = new StringBuilder();

        while (num > 0) {
            while (nums[i] <= num) {
                result.append(symbol[i]);
                num -= nums[i];
            }
            i++;
        }
        return result.toString();
    }
    private Boolean isGreater(String a, String b) {
        return Arrays.asList(symbol).indexOf(a) <= Arrays.asList(symbol).indexOf(b);
    }
    public int romanToInt(String s) {
        int result = 0, i = 0;
        for (i = 0; i < s.length() - 1; ++i) {
            if (!isGreater(s.charAt(i) + "", s.charAt(i + 1) + "")) {
                int index = Arrays.asList(symbol).indexOf(new StringBuilder().append(s.charAt(i)).append(s.charAt(i + 1)).toString());
                result += nums[index];
                ++i;
            } else {
                int index = Arrays.asList(symbol).indexOf(s.charAt(i) + "");
                result += nums[index];
            }
        }
        if (i == s.length() - 1) {
            int index = Arrays.asList(symbol).indexOf(s.charAt(i) + "");
            result += nums[index];
        }

        return result;
    }
}
