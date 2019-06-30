public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int [][] grid = new int [m][n];
        for (int i = 0; i < m; i++) {
            grid[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            grid[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                grid[i][j] = grid[i][j-1] + grid[i-1][j];
            }
        }
        return grid[m - 1][n - 1];
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int [][] grid = new int [m][n];
        boolean obstacle = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) { obstacle = true; }
            grid[i][0] = obstacle ? 0 : 1;
        }
        obstacle = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) { obstacle = true; }
            grid[0][i] = obstacle ? 0 : 1;
        }
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                grid[i][j] = obstacleGrid[i][j] == 1 ? 0 : grid[i][j-1] + grid[i-1][j];
            }
        }
        return grid[m - 1][n - 1];
    }
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
