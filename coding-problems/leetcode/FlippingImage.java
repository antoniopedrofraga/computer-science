/**
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 *
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * Example 1:
 *
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 * Example 2:
 *
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 *
 * Time complexity: O(nm)
 * Memory complexity: O(1)
 */
public class FlippingImage {
    public int[][] flipAndInvertImage(int[][] A) {
        for (int k = 0; k < A.length; k++) {
            int i = 0, j = A[k].length - 1;
            while (i <= j) {
                int temp = A[k][j];
                A[k][j] = A[k][i];
                A[k][i] = temp;
                A[k][i] = A[k][i] == 1 ? 0 : 1;
                if (i != j) {
                    A[k][j] = A[k][j] == 1 ? 0 : 1;
                }
                i++; j--;
            }
        }
        return A;
    }
}
