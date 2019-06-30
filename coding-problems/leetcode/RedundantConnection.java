import java.util.Arrays;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 *
 * Update (2017-09-26):
 * We have overhauled the problem description + test cases and specified clearly the graph is an undirected graph. For the directed graph follow up please see Redundant Connection II). We apologize for any inconvenience caused.
 *
 *
 * Approach: Use union find / disjoint set
 * Time complexity: O(n α(n))
 * Memory complexity: O(n)
 */

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int [] toRemove = new int[]{-1, -1};
        int [] reps = new int[edges.length + 1];
        for (int [] edge : edges) {
            int a = edge[0], b = edge[1];
            if (find(reps, a) == find(reps, b)) {
                toRemove = edge;
            } else {
                union(reps, a, b);
            }
        }
        return toRemove;
    }
    public int find(int [] reps, int x) {
        if (reps[x] == 0) reps[x] = x;
        if (reps[x] == x) return x;
        reps[x] = find(reps, reps[x]);
        return reps[x];
    }
    public void union(int [] reps, int x, int y) {
        x = find(reps, x);
        y = find(reps, y);
        if (x != y) {
            reps[y] = x;
        }
    }
    public void test() {
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
                {1,2}, {1,3}, {2,3}
        })));
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
                {1,2}, {2,3}, {3,4}, {1,4}, {1,5}
        })));
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{
                {9,10},{5,8},{2,6},{1,5},{3,8},{4,9},{8,10},{4,10},{6,8},{7,9}
        })));
    }
}
