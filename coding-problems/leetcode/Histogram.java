import java.util.Stack;

/**
 * A brief description
 *
 * @author Antonio Fraga (antonio.fraga@feedzai.com)
 * @since 18.10.0
 */
public class Histogram {
    public int largestRectangleArea(int [] heights) {
        if (heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0, maxArea = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int j = stack.pop();
                int area = heights[j] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(area, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int area = heights[j] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

}
