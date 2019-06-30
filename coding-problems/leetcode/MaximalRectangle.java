import java.util.Arrays;
import java.util.Stack;

/*
    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example:

    Input:
    [
    ["1","0","1","0","0"],
    ["1","0","1","1","1"],
    ["1","1","1","1","1"],
    ["1","0","0","1","0"]
    ]

    Output: 6

    Approach: Update heights of 1 in place. Run histogram algorithm.
    Time Complexity: O(m) with m being the matrix width.
    Memory Complexity: O(mn)
*/

public class MaximalRectangle {
    public int maximalRectangle(char [][] matrix) {
        int maxArea = 0;
        for (int y = 0; y < matrix.length; y++) {
            if (y > 0) {
                for (int x = 0; x < matrix[y].length; x++) {
                    if (matrix[y][x] == '1') {
                        int value = toInt(matrix[y - 1][x]) + 1;
                        matrix[y][x] = toChar(value);
                    }
                }
            }
            int area = largestRectangleArea(matrix[y]);
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }


    private int largestRectangleArea(char [] heights) {
        if (heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0, maxArea = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || toInt(heights[stack.peek()]) <= toInt(heights[i])) {
                stack.push(i++);
            } else {
                int j = stack.pop();
                int area = toInt(heights[j]) * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                maxArea = Math.max(area, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int area = toInt(heights[j]) * (stack.isEmpty() ? i : (i - stack.peek() - 1));
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    private int toInt(char character) {
        return character - '0';
    }
    private char toChar(int value) {
        return (char)(value + '0');
    }

}
