/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 */

public class MatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {  return; }
        int m = matrix.length, n = matrix[0].length;
        boolean firstRow = false, firstColumn = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstRow = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstColumn = true;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstColumn) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
