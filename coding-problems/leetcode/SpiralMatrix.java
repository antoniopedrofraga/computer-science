import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    private static int RIGHT = 0, DOWN = 1, LEFT = 2, UP = 3;

    public List<Integer> spiralOrder(int [][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        } else if (matrix[0].length == 1) {
            for (int [] array : matrix) {
                result.add(array[0]);
            }
            return result;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int move = 0, nrMoves = 0, x = 0, y = 0;
        boolean [][] visited = new boolean[rows][columns];
        while (nrMoves < rows * columns) {
            result.add(matrix[y][x]);
            visited[y][x] = true;

            if (move == RIGHT) {
                x++;
            } else if (move == DOWN) {
                y++;
            } else if (move == LEFT) {
                x--;
            } else if (move == UP) {
                y--;
            }
            if ((move == UP && (y - 1 < 0 || visited[y - 1][x]))
                || (move == DOWN && (y + 1 >= rows || visited[y + 1][x]))
                    || (move == LEFT && (x - 1 < 0  || visited[y][x - 1]))
                        || (move == RIGHT && (x + 1 >= columns || visited[y][x + 1]))) {
                move = (move + 1) % 4;
            }
            nrMoves++;
        }
        return result;
    }

    public int[][] generateMatrix(int n) {
        int [][] matrix = new int[n][n];
        int move = 0, nrMoves = 1, x = 0, y = 0;
        while (nrMoves <= Math.pow(n, 2)) {
            matrix[y][x] = nrMoves;

            if (move == RIGHT) {
                x++;
            } else if (move == DOWN) {
                y++;
            } else if (move == LEFT) {
                x--;
            } else if (move == UP) {
                y--;
            }
            if ((move == UP && (y - 1 < 0 || matrix[y - 1][x] != 0))
                    || (move == DOWN && (y + 1 >= n ||  matrix[y + 1][x] != 0))
                    || (move == LEFT && (x - 1 < 0  ||  matrix[y][x - 1] != 0))
                    || (move == RIGHT && (x + 1 >= n ||  matrix[y][x + 1] != 0))) {
                move = (move + 1) % 4;
            }
            nrMoves++;
        }
        return matrix;
    }
}
