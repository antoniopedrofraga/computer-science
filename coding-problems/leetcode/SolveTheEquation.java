/**
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.
 *
 * If there is no solution for the equation, return "No solution".
 *
 * If there are infinite solutions for the equation, return "Infinite solutions".
 *
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 *
 * Example 1:
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 * Example 2:
 * Input: "x=x"
 * Output: "Infinite solutions"
 * Example 3:
 * Input: "2x=x"
 * Output: "x=0"
 * Example 4:
 * Input: "2x+3x-6x=x+2"
 * Output: "x=-1"
 * Example 5:
 * Input: "x=x+2"
 * Output: "No solution"
 *
 * Time complexity: O(n)
 * Memory complexity: O(n) worst case, but we can easily turn it into O(1)
 */

public class SolveTheEquation {
    public String solveEquation(String equation) {
        int xLeftSide = 0, leftSide = 0,
                xRightSide = 0, rightSide = 0;
        boolean left = true, assigned = false;
        char lastSign = '+'; boolean x = false;
        int current = 0;
        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == 'x') {
                x = true;
            } else if (Character.isDigit(equation.charAt(i))) {
                int digit = equation.charAt(i) - '0';
                current = current * 10 + digit;
                assigned = true;
            }
            if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '=' || i + 1 == equation.length()) {
                int value = current == 0 ? (x && !assigned ? 1 : 0) : current;
                if (lastSign == '-') {
                    value = -value;
                }
                if (x) {
                    if (left) {
                        xLeftSide += value;
                    } else {
                        xRightSide += value;
                    }
                } else {
                    if (left) {
                        leftSide += value;
                    } else {
                        rightSide += value;
                    }
                }
                current = 0;
                if (equation.charAt(i) == '=') {
                    left = false;
                    lastSign = '+';
                } else {
                    lastSign = equation.charAt(i);
                }
                x = false;
                assigned = false;
            }
        }
        xLeftSide -= xRightSide;
        rightSide -= leftSide;
        if (xLeftSide == 0 && rightSide != 0) {
            return "No solution";
        } else if (xLeftSide == 0) {
            return "Infinite solutions";
        }
        if (xLeftSide < 0) {
            xLeftSide = -xLeftSide;
            rightSide = -rightSide;
        }
        return "x=" + (rightSide / xLeftSide);
    }

    public void test() {
        System.out.println(solveEquation("x+5-3+x=6+x-2"));
        System.out.println(solveEquation("x=x"));
        System.out.println(solveEquation("2x=x"));
        System.out.println(solveEquation("2x+3x-6x=x+2"));
        System.out.println(solveEquation("x=x+2"));
        System.out.println(solveEquation("0x=0"));
    }

}
