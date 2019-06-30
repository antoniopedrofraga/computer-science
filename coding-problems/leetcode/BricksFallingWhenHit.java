import java.util.Arrays;

/**
 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the top of the grid, or at least one of its (4-way) adjacent bricks will not drop.
 *
 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that location will disappear, and then some other bricks may drop because of that erasure.
 *
 * Return an array representing the number of bricks that will drop after each erasure in sequence.
 *
 * Example 1:
 * Input:
 * grid = [[1,0,0,0],[1,1,1,0]]
 * hits = [[1,0]]
 * Output: [2]
 * Explanation:
 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
 * Example 2:
 * Input:
 * grid = [[1,0,0,0],[1,1,0,0]]
 * hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation:
 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
 *
 *
 * Note:
 *
 * The number of rows and columns in the grid will be in the range [1, 200].
 * The number of erasures will not exceed the area of the grid.
 * It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
 * An erasure may refer to a location with no brick - if it does, no bricks drop.
 *
 *
 * This solution has a time complexity of O(n) (n being the number of cells in the grid). The memory complexity is O(1). We're doing things in-place.
 */
public class BricksFallingWhenHit {
    static final int HIT = -1;
    static final int BRICK = 1;
    static final int VISITED = 2;
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int [] result = new int[hits.length];
        for (int [] hit : hits) {
            if (grid[hit[0]][hit[1]] == BRICK) {
                grid[hit[0]][hit[1]] = HIT;
            }
        }
        for (int j = 0; j < grid[0].length; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, 0, j);
            }
        }
        for (int k = result.length - 1; k >= 0; k--) {
            int i = hits[k][0], j = hits[k][1];
            if (grid[i][j] == HIT) {
                grid[i][j] = BRICK;
                if (isConnected(grid, i, j)) result[k] = dfs(grid, i, j) - 1;
            }
        }
        return result;
    }
    public boolean isConnected(int [][] grid, int i, int j) {
        return i - 1 < 0 || (grid[i - 1][j] == VISITED) ||
                (i + 1 < grid.length && grid[i + 1][j] == VISITED) ||
                (j - 1 >= 0 && grid[i][j - 1] == VISITED) ||
                (j + 1 < grid[0].length && grid[i][j + 1] == VISITED);
    }
    public int dfs(int [][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != BRICK) {
            return 0;
        }
        grid[i][j] = VISITED;
        int sum = 1;
        sum += dfs(grid, i + 1, j);
        sum += dfs(grid,  i - 1, j);
        sum += dfs(grid, i, j + 1);
        sum += dfs(grid, i, j - 1);
        return sum;
    }
    public void test() {
        System.out.println(
                Arrays.toString(hitBricks(
                        new int[][]{
                                {1,0,0,0},
                                {1,1,1,0}
                        },
                        new int[][]{
                                {1,0}
                        }
                ))
        );
        System.out.println(
                Arrays.toString(hitBricks(
                        new int[][]{
                                {1,0,0,0},
                                {1,1,0,0}
                        },
                        new int[][]{
                                {1,1},
                                {1,0}
                        }
                ))
        );
        System.out.println(
                Arrays.toString(hitBricks(
                        new int[][]{
                                {1},
                                {1},
                                {1},
                                {1},
                                {1}
                        },
                        new int[][]{
                                {3,0},
                                {4,0},
                                {1,0},
                                {2,0},
                                {0,0}
                        }
                ))
        );
        System.out.println(
                Arrays.toString(hitBricks(
                        new int[][]{
                                {1,0,1},
                                {1,1,1}
                        },
                        new int[][]{
                                {0,0},
                                {0,2},
                                {1,1}
                        }
                ))
        );
        System.out.println(
                Arrays.toString(hitBricks(
                        new int[][]{
                                {1,1,1,0,1,1,1,1},
                                {1,0,0,0,0,1,1,1},
                                {1,1,1,0,0,0,1,1},
                                {1,1,0,0,0,0,0,0},
                                {1,0,0,0,0,0,0,0},
                                {1,0,0,0,0,0,0,0}
                        },
                        new int[][]{
                                {4,6},
                                {3,0},
                                {2,3},
                                {2,6},
                                {4,1},
                                {5,2},
                                {2,1}
                        }
                ))
        );
    }
}
