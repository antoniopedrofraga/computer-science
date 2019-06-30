import java.util.Stack;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 * Input: "bcabc"
 * Output: "abc"
 *
 * Example 2:
 * Input: "cbacdcbc"
 * Output: "acdb"
 *
 * Approach: Use a stack to build the string. Check if the peek of the stack corresponds to a character that may occur after the current character (in case it is lexically greater).
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */

public class DuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s.length() == 0) return s;
        Stack<Character> stack = new Stack<>();
        int[] letterCount = new int[26];
        boolean[] visited = new boolean[26];
        s.chars().forEach(c -> letterCount[c - 'a']++);
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            letterCount[current - 'a']--;
            if (visited[current - 'a']) continue;
            while (!stack.isEmpty() && stack.peek() > current && letterCount[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(current);
            visited[current - 'a'] = true;
        }
        StringBuilder builder = new StringBuilder();
        for (char c : stack) {
            builder.append(c);
        }
        return builder.toString();
    }
}
