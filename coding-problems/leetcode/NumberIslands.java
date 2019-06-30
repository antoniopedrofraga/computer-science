import javafx.util.Pair;
import java.util.Stack;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 * Example 2:
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * Approach: Run DFS in a given island in order to discover it and mark its nodes as discovered.
 * Time complexity: O(n * m);
 * Memory complexity: O(n * m);
 */
public class NumberIslands {
    public void dfs(boolean [][] visited, char [][] grid, int i, int j, int m, int n) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(i, j));
        while (!stack.isEmpty()) {
            Pair<Integer, Integer> pair = stack.pop();
            i = pair.getKey();
            j = pair.getValue();
            if (i < 0 || i >= m) continue;
            if (j < 0 || j >= n) continue;
            if (grid[i][j] != '1' || visited[i][j]) continue;
            visited[i][j] = true;
            stack.push(new Pair<>(i - 1, j));
            stack.push(new Pair<>(i + 1, j));
            stack.push(new Pair<>(i, j - 1));
            stack.push(new Pair<>(i, j + 1));
        }
    }
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int nIslands = 0;
        boolean [][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(visited, grid, i, j, m, n);
                    nIslands++;
                }
            }
        }
        return nIslands;
    }
}
