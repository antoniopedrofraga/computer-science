/**
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 *
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
 *
 * (Note also that a translation does not include any kind of rotation.)
 *
 * What is the largest possible overlap?
 *
 * Example 1:
 *
 * Input: A = [[1,1,0],
 *             [0,1,0],
 *             [0,1,0]]
 *        B = [[0,0,0],
 *             [0,1,1],
 *             [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 * Notes:
 *
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */
public class LargestOverlap {
public int largestOverlap(int[][] A, int[][] B) {
    int height = A.length;
    if (height == 0) return 0;
    int width = A[0].length;
    int[][] countA = new int[height][width];
    int[][] countB = new int[height][width];

    for (int deltai = 0; deltai < height; deltai++) {
        for (int deltaj = 0; deltaj < width; deltaj++) {
            for (int i = deltai; i < height; i++) {
                int k = i - deltai;
                for (int j = deltaj; j < width; j++) {
                    int l = j - deltaj;
                    if (A[k][l] == 1 && B[i][j] == 1) {
                        countA[deltai][deltaj]++;
                    }
                    if (B[k][l] == 1 && A[i][j] == 1) {
                        countB[deltai][deltaj]++;
                    }

                }
            }
        }
    }

    int max = 0;
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            max = Math.max(max, countA[i][j]);
            max = Math.max(max, countB[i][j]);
        }
    }
    return max;
}
    public void test() {
        System.out.println(largestOverlap(new int[][]{
                {1,1,0},
                {0,1,0},
                {0,1,0}
        }, new int[][]{
                {0,0,0},
                {0,1,1},
                {0,0,1}
        }));
    }
}
