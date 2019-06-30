/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *
 *
 * Note: Do not use the eval built-in library function.
 *
 * Approach: Use a stack or recursion to evaluate inner expressions.
 * Memory complexity: O(n)
 * Time complexity: O(n)
 */
public class BasicCalculatorIII {
    public int calculate(String s) {
        int [] result = calculate(s, 0);
        return result[0];
    }

    public int[] calculate(String s, int i) {
        long accumulated = 0, current = 0;
        char lastSign = '+';
        StringBuilder builder = new StringBuilder();
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                i++;
                int [] result = calculate(s, i);
                int value = result[0];
                current = getCurrent(value, lastSign, current);
                i = result[1];
                continue;
            } else if (Character.isDigit(s.charAt(i))) {
                builder.append(s.charAt(i));
            }  else if (s.charAt(i) == '*' || s.charAt(i) == '/' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-') {
                if (builder.length() > 0) {
                    long value = Long.parseLong(builder.toString());
                    current = getCurrent(value, lastSign, current);
                }
                lastSign = s.charAt(i);
                if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                    accumulated += current;
                    current = 0;
                }
                builder.setLength(0);
            } else if (s.charAt(i) == ')') {
                i++;
                break;
            }
            i++;
        }
        if (builder.length() > 0) {
            long value = Long.parseLong(builder.toString());
            current = getCurrent(value, lastSign, current);
        }
        accumulated += current;
        return new int[]{(int)accumulated, i};
    }

    long getCurrent(long value, char lastSign, long current) {
        if (lastSign == '-') {
            current -= value;
        } else if (lastSign == '+') {
            current += value;
        } else if (lastSign == '*') {
            current *= value;
        } else if (lastSign == '/') {
            current /= value;
        }
        return current;
    }

    public void test() {
        String [] tests = new String[]{"1 + 1", " 6-4 / 2 ", "2*(5+5*2)/3+(6/2+8)", "(2+6* 3+5- (3*14/7+2)*5)+3", "0-2147483648", "((   (   (   4-   2)+ ( 6+   10 )   )+ 1)   /(  (  (   7  +   9 )*   (   5*8)   )-   (  5 +  (   2   *   10   ) ) ) )"};
        int [] expected = new int[]{2, 4, 21, -12, -2147483648, 0};
        for (int i = 0; i < tests.length; i++) {
            int result = calculate(tests[i]);
            if (result != expected[i]) {
                System.out.println("Testing \"" + tests[i] + "\" expected " + expected[i] + " but got " + result);
            }
        }
    }
}
