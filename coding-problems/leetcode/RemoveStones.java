import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.
 *
 * Now, a move consists of removing a stone that shares a column or row with another stone on the grid.
 *
 * What is the largest possible number of moves we can make?
 *
 *
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
public class RemoveStones {
    Map<Integer, Integer> f = new HashMap<>();
    int islands = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i)
            union(stones[i][0], stones[i][1] + 10000);
        return stones.length - islands;
    }

    private int find(int x) {
        if (f.putIfAbsent(x, x) == null)
            islands++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }

    public void test() {
        System.out.println(removeStones(new int[][]{{5,9},{9,0},{0,0},{7,0},{4,3},{8,5},{5,8},{1,1},{0,6},{7,5},{1,6},{1,9},{9,4},{2,8},{1,3},{4,2},{2,5},{4,1},{0,2},{6,5}}));
        System.out.println(removeStones(new int[][]{{1,2},{3,2},{3,0},{4,4},{4,2},{2,4},{4,0}}));
        System.out.println(removeStones(new int[][]{{4,5},{0,4},{0,5},{4,3},{2,2},{5,1},{0,3},{2,4},{4,0}}));
        System.out.println(removeStones(new int[][]{{3,2},{0,0},{3,3},{2,1},{2,3},{2,2},{0,2}}));
        System.out.println(removeStones(new int[][]{{0,1},{1,0},{1,1}}));
        System.out.println(removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}));
        System.out.println(removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}));
    }
}
