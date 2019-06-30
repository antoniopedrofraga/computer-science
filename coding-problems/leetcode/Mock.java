import java.util.Arrays;

/**
 * A dummy description of Mock
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class Mock {
    class Wrapper {
        int leftDiag;
        int rightDiag;
        int hor;
        int vert;
        public Wrapper() {
            this.leftDiag = 0;
            this.rightDiag = 0;
            this.hor = 0;
            this.vert = 0;
        }
        int getMax() {
            return Math.max(rightDiag, Math.max(this.leftDiag, Math.max(this.hor, this.vert)));
        }
    }
    public int longestLine(int[][] M) {
        if (M.length == 0) return 0;
        int m = M.length + 1, n = M[0].length + 1;
        Wrapper [][] dp = new Wrapper[m][n + 1];
        for (Wrapper [] wrappers : dp) {
            for (int i = 0; i < wrappers.length; i++) {
                wrappers[i] = new Wrapper();
            }
        }
        int max = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (M[i - 1][j - 1] == 1) {
                    Wrapper wrapper = dp[i][j];
                    wrapper.hor = dp[i][j - 1].hor + 1;
                    wrapper.leftDiag = dp[i - 1][j - 1].leftDiag + 1;
                    wrapper.rightDiag = dp[i - 1][j + 1].rightDiag + 1;
                    wrapper.vert = dp[i - 1][j].vert + 1;
                    max = Math.max(max, wrapper.getMax());
                }
            }
        }
        return max;
    }

    public boolean validMountainArray(int[] A) {
        if (A.length < 3) return false;
        boolean upwards = true;
        int up = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) return false;
            if (upwards) {
                if (A[i] < A[i - 1]) upwards = false;
                else up++;
            } else {
                if (A[i] > A[i - 1]) return false;
            }
        }
        return !upwards && up > 0;
    }


    public void test() {
        int [][] arr = new int [][] {
            {1,1,0,0,1,0,0,1,1,0},
            {1,0,0,1,0,1,1,1,1,1},
            {1,1,1,0,0,1,1,1,1,0},
            {0,1,1,1,0,1,1,1,1,1},
            {0,0,1,1,1,1,1,1,1,0},
            {1,1,1,1,1,1,0,1,1,1},
            {0,1,1,1,1,1,1,0,0,1},
            {1,1,1,1,1,0,0,1,1,1},
            {0,1,0,1,1,0,1,1,1,1},
            {1,1,1,0,1,0,1,1,1,1}
        };
        System.out.println(longestLine(arr));
    }
}
