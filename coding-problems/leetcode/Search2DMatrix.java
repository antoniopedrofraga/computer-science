/**
 * Approach: Start in the top left corner and iterate over cols until we have a number which is larger than the target, in that case iterate over rows.
 *
 * Time complexity: O(m + n)
 * Memory complexity: O(1)
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            }
        }
        return false;
    }

}
