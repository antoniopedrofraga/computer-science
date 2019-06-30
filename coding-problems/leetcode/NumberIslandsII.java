import java.util.HashMap;
import java.util.Map;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 *
 * Approach: Solve this with disjoint set.
 */
public class NumberIslandsII {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int width = grid[0].length, islands = 0;
        Map<Integer, Integer> reps = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '0') continue;
                Integer up = null, left = null, index;
                if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                    index = (i - 1) * width + j;
                    up = getRep(reps, index);
                }
                if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                    index = i * width + (j - 1);
                    left = getRep(reps, index);
                }
                index = i * width + j;
                islands += union(reps, left, up, index);
            }
        }
        return islands;
    }
    int union(Map<Integer, Integer> reps, Integer left, Integer up, int index) {
        int result = 0;
        if (left == null && up == null) {
            result++;
            reps.put(index, index);
        } else if (up == null) {
            reps.put(index, left);
        } else if (left == null ) {
            reps.put(index, up);
        } else {
            if (!left.equals(up)) {
                result--;
                reps.put(up, left);
            }
            reps.put(index, left);
        }
        return result;
    }
    int getRep(Map<Integer, Integer> reps, int index) {
        if (reps.get(index) == index) return index;
        reps.put(index, getRep(reps, reps.get(index)));
        return reps.get(index);
    }

    public void test() {
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
        System.out.println(numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        }));
    }
}
