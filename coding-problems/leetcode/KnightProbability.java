import javafx.util.Pair;

import java.util.*;

/**
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 *
 * A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
 *
 * Example:
 *
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 *
 * Memory complexity: O(n)
 * Time complexity: O(n * n * K)
 */
public class KnightProbability {
static int [][] moves = new int[][] { {2, -1}, {2 , 1}, {1, 2}, {-1, 2}, {-2 , 1}, {-2, -1}, {-1, -2}, {1, -2} };
public double knightProbability(int N, int K, int r, int c) {
    double [][] memo = new double[N * N][K + 1];
    double validWays = bottomUp(memo, N, K, r, c),
            totalWays = Math.pow(8, K);
    return validWays / totalWays;
}
public double bottomUp(double [][] memo, int N, int K, int r, int c) {
    if (K == 0) {
        return 1;
    }
    int index = r * N + c;
    if (memo[index][K] != 0) return memo[index][K];
    double validWays = 0;
    for (int [] move : moves) {
        int y = r + move[0], x = c + move[1];
        if (y >= 0 && y < N && x >= 0 && x < N) {
            validWays += bottomUp(memo, N,K - 1, y, x);
        }
    }
    memo[index][K] = validWays;
    return validWays;
}
    public void test() {
        List<Integer[]> params = Arrays.asList(new Integer[] {3, 2, 0, 0}, new Integer[]{8, 30, 6, 4});
        List<Double> expected = Arrays.asList(0.0625, 0.00019);
        for (int i = 0; i < params.size(); i++) {
            Integer [] p = params.get(i);
            double value = knightProbability(p[0], p[1], p[2], p[3]);
            System.out.println(Arrays.toString(p) + " expected " + expected.get(i) + " and got " + value);
        }
    }
}
