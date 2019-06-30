/**
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * Example:
 * Input:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 *
 * Output: 16
 *
 * Time complexity: O(mn)
 * Memory complexity: O(1)
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) continue;
                if (i - 1 < 0 || grid[i - 1][j] == 0) perimeter++;
                if (i + 1 >= grid.length || grid[i + 1][j] == 0) perimeter++;
                if (j - 1 < 0 || grid[i][j - 1] == 0) perimeter++;
                if (j + 1 >= grid[i].length || grid[i][j + 1] == 0) perimeter++;
            }
        }
        return perimeter;
    }
}
