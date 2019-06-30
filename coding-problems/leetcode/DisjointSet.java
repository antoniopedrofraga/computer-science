import java.util.HashMap;
import java.util.Map;

/**
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 * Example 1:
 *
 * Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * Output: 5
 * Example 2:
 *
 * Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * Output: 3
 * Example 3:
 *
 * Input: stones = [[0,0]]
 * Output: 0
 *
 * [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 *
 * Approach: We can use an union disjoint set.
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class DisjointSet {

    public int removeStones(int [][] stones) {
        Map<Integer, Integer> reps = new HashMap<>();
        int removedStones = 0, totalStones = 0;
        for (int i = 0; i < stones.length; i++) {
            for (int j = 0; j < stones[i].length; j++) {
                if (stones[i][j] == 1) {
                    totalStones++;
                    removedStones += union(reps, i, j + stones.length);
                }
            }
        }
        return totalStones - removedStones;
    }

    private int find(Map<Integer, Integer> reps, int x) {
        // if we don't have any stone in that col / row, this stone should be its own representative
        reps.putIfAbsent(x, x);

        // if we do have any stone in that col / row, this stone should take the existing representative
        // and update all representatives in the way
        if (x != reps.get(x))
            reps.put(x, find(reps, reps.get(x)));

        // return the actual representative
        return reps.get(x);
    }

    private int union(Map<Integer, Integer> reps, int y, int x) {
        int oldSize = reps.size(), removedStones = 0;

        // find column wise representative
        x = find(reps, x);

        // find row wise representative
        y = find(reps, y);

        // new representatives
        removedStones += reps.size() - oldSize;

        // will make sure that column wise values will have row wise representatives
        // (only if they are not the same already)
        if (x != y) {
            reps.put(x, y);
            removedStones--;
        }

        // return new islands (this value will be adjusted on future updates)
        return removedStones;
    }
}
