import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * My idea is to use a stack in order to implement a (DFS) backtracking solution.
 * Memory complexity: O(n nCr k)
 * Time complexity: O(n nCr k)
 */
public class Combine {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (!stack.isEmpty()) {
            if (stack.size() < k) {
                int top = stack.peek() + 1;
                if (top <= n) {
                    stack.push(top);
                } else {
                    stack.pop();
                    if (stack.size() > 0) {
                        top = stack.pop() + 1;
                        stack.push(top);
                    }
                }
            } else {
                List<Integer> combination = new LinkedList<>(stack);
                list.add(combination);
                int top = stack.pop() + 1;
                if (top <= n) {
                    stack.push(top);
                } else {
                    if (stack.size() > 0) {
                        top = stack.pop() + 1;
                        stack.push(top);
                    }
                }
            }
        }
        return list;
    }

}
