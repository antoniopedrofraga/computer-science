/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 *
 * Approach: Preprocess a sum matrix and then answer queries in constant time.
 * Time compleity: O(1) amortized
 * Memory complexity: O(1)
 */
public class NumMatrixII {
    public NumMatrixII(){}
    int [][] matrix;
    public NumMatrixII(int[][] matrix) {
        this.matrix = matrix;
        if (matrix.length == 0) return;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int left = getSum(i, j - 1);
                int diag = getSum(i - 1, j - 1);
                int up = getSum(i - 1, j);
                matrix[i][j] += (left + (up - diag));
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) - getSum(row2, col1 - 1) - getSum(row1 - 1, col2) + getSum(row1 - 1, col1 - 1);
    }
    int getSum(int row, int col) {
        return row >= 0 && col >= 0 ?  matrix[row][col] : 0;
    }

    public void test() {
        NumMatrixII matrix = new NumMatrixII(new int[][]{{-4, -5}});
        System.out.println(matrix.sumRegion(0,0,0,0));
        System.out.println(matrix.sumRegion(0,0,0,1));
        System.out.println(matrix.sumRegion(0,1,0,1));
    }
}
