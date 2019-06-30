import java.util.Stack;

/**
 * Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.
 *
 * Example 1:
 * Input: "1 + 1"
 * Output: 2
 *
 * Example 2:
 * Input: " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 *
 * Approach: Use a stack to calculate “inner calculations”, this can be translated into a dfs.
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class BasicCalculator {
    class Calculation {
        Character sign;
        int value;
        public Calculation(Character sign, int value) {
            this.sign = sign;
            this.value = 0;
        }
    }
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        char previousSign = '+';
        StringBuilder currValue = new StringBuilder();
        Stack<Calculation> stack = new Stack<>();
        stack.push(new Calculation('+', 0));
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(new Calculation(previousSign, 0));
                previousSign = '+';
            } else if (s.charAt(i) == ')') {
                int value = currValue.length() == 0 ? 0 : Integer.valueOf(currValue.toString());
                currValue.setLength(0);
                stack.peek().value += previousSign == '+' ? value : -value;
                Calculation calc = stack.pop();
                value = calc.value;
                previousSign = calc.sign;
                stack.peek().value += previousSign == '+' ? value : -value;
            } else if (Character.isDigit(s.charAt(i))) {
                currValue.append(s.charAt(i));
            } else {
                int value = currValue.length() == 0 ? 0 : Integer.valueOf(currValue.toString());
                currValue.setLength(0);
                stack.peek().value += previousSign == '+' ? value : -value;
                previousSign = s.charAt(i);
            }
        }
        if (currValue.length() > 0) {
            int value = Integer.valueOf(currValue.toString());
            stack.peek().value += previousSign == '+' ? value : -value;
        }
        return stack.peek().value;
    }


}
