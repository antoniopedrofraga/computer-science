/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 *
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class AddStrings {
    char backwardsCharAt(String n, int i) {
        if (i >= n.length()) return '0';
        int j = n.length() - i - 1;
        return n.charAt(j);
    }
    public String addStrings(String num1, String num2) {
        int i = 0, j = 0, carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i < num1.length() || j < num2.length() || carry > 0) {
            int a = 0, b = 0, current = 0;
            a = backwardsCharAt(num1, i++) - '0';
            b = backwardsCharAt(num2, j++) - '0';
            current = a + b + carry;
            carry = current / 10;
            current %= 10;
            char val = (char) (current + '0');
            builder.append(val);
        }
        builder.reverse();
        return builder.toString();
    }

    public void test() {
        System.out.println(addStrings("98", "9"));
    }
}
