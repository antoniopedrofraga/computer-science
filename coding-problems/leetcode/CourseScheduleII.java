import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 *
 *
 * I've seen some complicated solutions, therefore I've decided to post my solution, which I believe to be simple.
 *
 * This is a problem of topological sorting. We know that we should only add courses to the result array once its dependecies are already in the array. Courses with no dependecies can be added in any order. What if we do it backwards? We add courses one by one, following dependecies. In the end we only need to revert the array to make it valid.
 *
 * Hence, we can formulate our algorithm as follows,
 *
 * TopologicalSort(G):
 *     call DFS(G)
 *     add vertex to an array as each vertex discovering process comes to an end
 *
 * If our Depth First Search contains cycles (tries to discover a course that is currently being discovered), then we know that we can't build a valid list of courses. We can create a status array in order to know whether the i-th course is currently being discovered, and use backtracking to update it once the whole discovering process finishes.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */

public class CourseScheduleII {
    final static int INVALID = -1, NON_VISITED = 0, VISITED = 1, DISCOVERING = 2;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> [] postreq = buildPostrequisites(prerequisites, numCourses);
        int [] status = new int[numCourses];
        int [] result = new int[numCourses];
        int j = 0, i = 0;
        for (; i < numCourses; i++) {
            j = dfs(status, postreq, i, result, j);
            if (j == INVALID) return new int[]{};
        }
        return result;
    }
    /*
        postreq[i] should hold all courses that have the i-th course as prerequesite.
		these courses will be added first to the result array (because we are running a Depth First Search)
    */
    List<Integer> [] buildPostrequisites(int[][] prerequisites, int numCourses) {
        List<Integer> [] postreq = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            postreq[i] = new ArrayList<>();
        }
        for (int [] p : prerequisites) {
            int before = p[0], after = p[1];
            postreq[before].add(after);
        }
        return postreq;
    }

    /*
        Run dfs and discover cycles by using backtracking. As we are discovering a course, we should mark it as "Discovering".
        If we try to discover a node marked as "Discovering", we have a cycle.
        If a node was successfuly explored, we can mark it as "Explored" (courses were already added to the result array):
    */
    public int dfs(int [] status, List<Integer> [] postreq, int i, int [] result, int j) {
        if (status[i] == VISITED) return j;
        if (status[i] == DISCOVERING) return INVALID;
        status[i] = DISCOVERING;
        for (int p : postreq[i]) {
            j = dfs(status, postreq, p, result, j);
            if (j == INVALID) return INVALID;
        }
        status[i] = VISITED;
        result[j++] = i;
        return j;
    }

    public void test() {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1,0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }
}
