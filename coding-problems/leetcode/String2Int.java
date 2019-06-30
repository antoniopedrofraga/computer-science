public class String2Int {
    public int myAtoi(String str) {
        int result = 0, left = 0;
        boolean minus = false;

        str = str.trim();
        if (str.length() == 0) return 0;

        if (str.charAt(0) == '-') {
            minus = true;
            left++;
        } else if (str.charAt(0) == '+') {
            left++;
        }

        int right = left;
        for (; right < str.length() && Character.isDigit(str.charAt(right)); right++) {}
        right--;

        for (int count = 0; right >= left; right--, count++) {

            if ((long)Character.getNumericValue(str.charAt(right)) * Math.pow(10, count) > Integer.MAX_VALUE) {
                return minus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            int value = Character.getNumericValue(str.charAt(right)) * (int)Math.pow(10, count);
            if (!minus && Integer.MAX_VALUE - value < result) {
                return Integer.MAX_VALUE;
            } else if (minus && Integer.MIN_VALUE + value > -result) {
                return Integer.MIN_VALUE;
            }
            result += value;
        }
        return minus ? -result : result;
    }
}
