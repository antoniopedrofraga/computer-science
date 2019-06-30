import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Input: ")()((())("
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 *
 * Approach: Use a stack with a cover position in case we have contiguous sequences of valid parentheses.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int max = 0, left = Integer.MAX_VALUE;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
    public void test() {
        System.out.println("(() -> " + longestValidParentheses("(()"));
        System.out.println(")()()) -> " + longestValidParentheses(")()())"));
        System.out.println("(()() -> " + longestValidParentheses("(()()"));
        System.out.println("()(()) -> " + longestValidParentheses("()(())"));
        System.out.println("()(() -> " + longestValidParentheses("()(()"));
        System.out.println("()((())() -> " + longestValidParentheses("()((())()"));
    }
}
