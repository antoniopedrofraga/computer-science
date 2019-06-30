/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 * Approach: Use normal multiplication
 * Time complexity: O(n m)
 * Memory complexity: O(n m)
 */
public class MultiplyStrings {
    public String multiply(String x, String y) {
        if ("0".equals(x) || "0".equals(y)) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        StringBuilder [] builders = new StringBuilder[y.length()];
        for (int i = y.length() - 1; i >= 0; i--) {
            int l = y.length() - 1 - i;
            int carry = 0, b = Character.getNumericValue(y.charAt(i));
            builders[i] = new StringBuilder();
            for (int k = y.length() - 1; k > i; k--) {
                builders[i].append('0');
            }
            for (int j = x.length() - 1; j >= 0; j--) {
                int a = Character.getNumericValue(x.charAt(j));
                int c = a * b + carry;
                carry = c / 10;
                c %= 10;
                builders[i].append(c);
            }
            while (carry > 0) {
                builders[i].append(carry % 10);
                carry /= 10;
            }
        }
        for (StringBuilder builder : builders) {
            result = reverseAdd(result, builder);
        }
        result.reverse();
        return result.toString();
    }
    StringBuilder reverseAdd(StringBuilder x, StringBuilder y) {
        StringBuilder result = new StringBuilder();
        StringBuilder max = x.length() < y.length() ? y : x;
        StringBuilder min = y.length() <= x.length() ? y : x;
        int i = 0, carry = 0;
        for (;i < min.length(); i++) {
            int b = Character.getNumericValue(min.charAt(i)),
                    a = Character.getNumericValue(max.charAt(i));
            carry += a + b;
            result.append(carry % 10);
            carry /= 10;
        }
        for (; i < max.length(); i++) {
            int a = Character.getNumericValue(max.charAt(i));
            carry += a;
            result.append(carry % 10);
            carry /= 10;
        }
        while (carry > 0) {
            result.append(carry % 10);
            carry /= 10;
        }
        return result;
    }
    public void test() {
        System.out.println(multiply("237", "284"));
        System.out.println(multiply("9133", "0"));
        System.out.println(multiply("9", "9"));
        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
    }
}
