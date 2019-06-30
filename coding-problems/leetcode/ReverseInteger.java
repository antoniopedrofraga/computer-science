
public class ReverseInteger {
    public int reverse(int x) {
        boolean negative = x < 0;
        StringBuilder result = x == 0 ? new StringBuilder("0") : new StringBuilder();
        long longResult = 0;
        long y = Math.abs((long) x);
        while (y > 0) {
            int digit = (int) (y % 10);
            result.append(Integer.toString(digit));
            y /= 10;
        }
        longResult = Long.parseLong(result.toString());
        if (longResult > Integer.MAX_VALUE || longResult < Integer.MIN_VALUE) {
            longResult = 0;
        }
        return (int)(negative ? -longResult : longResult);
    }
}
