/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 *
 * Time complexity: O(nm)
 * Memory Complexity: O(1)
 */
public class GameOfLife {
    private static final int DIES = 2;
    private static final int BORN = -1;

    int countLiveNeighbours(int [][] board, int i, int j, int m, int n) {
        int counter = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if (k == 0 && l == 0) continue;
                int newI = i + k, newJ = j + l;
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && board[newI][newJ] > 0) {
                    counter++;
                }
            }
        }
        return counter;
    }
    public void gameOfLife(int[][] board) {
        if (board.length == 0) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbours = countLiveNeighbours(board, i, j, m, n);
                if (board[i][j] == 1 && (liveNeighbours < 2 || liveNeighbours > 3)) {
                    board[i][j] = DIES;
                } else if (board[i][j] == 0 && liveNeighbours == 3) {
                    board[i][j] = BORN;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == DIES) {
                    board[i][j] = 0;
                } else if (board[i][j] == BORN) {
                    board[i][j] = 1;
                }
            }
        }
    }

}
