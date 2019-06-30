public class SearchMatrix {
    public boolean searchMatrix(int [][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int low = 0, high = matrix.length - 1, i = 0;
        if (target == matrix[high][0] || target == matrix[low][0]) return true;
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][0] >= target) {
                high = mid;
            } else {
                low = mid;
            }
            if (target == matrix[high][0] || target == matrix[low][0]) return true;
        }
        i = matrix[high][0] < target ? high : low;
        low = 0;
        high = matrix[i].length - 1;
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (matrix[i][mid] >= target) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return target == matrix[i][low] || target == matrix[i][high];
    }
}
