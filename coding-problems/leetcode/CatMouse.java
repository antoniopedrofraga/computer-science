import java.util.LinkedList;
import java.util.Queue;

/**
 * A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
 *
 * The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
 *
 * Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.
 *
 * During each player's turn, they must travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it must travel to any node in graph[1].
 *
 * Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
 *
 * Then, the game can end in 3 ways:
 *
 * If ever the Cat occupies the same node as the Mouse, the Cat wins.
 * If ever the Mouse reaches the Hole, the Mouse wins.
 * If ever a position is repeated (ie. the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
 * Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 2 if the game is won by Cat, and 0 if the game is a draw.
 *
 *
 *
 * Example 1:
 *
 * Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * Output: 0
 * Explanation:
 * 4---3---1
 * |   |
 * 2---5
 *  \ /
 *   0
 *
 *
 * Note:
 *
 * 3 <= graph.length <= 50
 * It is guaranteed that graph[1] is non-empty.
 * It is guaranteed that graph[2] contains a non-zero element.
 *
 * Approach: Apply a bottom up solution. Start from the nodes where we are sure that the cat wins or the cat wins. Build the answer from there until we are sure that there are no more possible moves.
 * Time complexity: O(n 3)
 * Memory complexity: O(n 2)
 */
public class CatMouse {
    private static final int MOUSE_TURN = 0, CAT_TURN = 1, MOUSE_WINS = 1, CAT_WINS = 2, DRAW = 0;
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        Queue<int []> q = new LinkedList<>();
        // we define a state to be (mouse pos, cat pos and turn)
        int [][] states = new int[n * n][2];
        int [][] children = new int[n * n][2];

        for (int i = 0; i < n * n; i++) {
            int m = i / n, c = i % n;
            children[i][MOUSE_TURN] = graph[m].length;
            children[i][CAT_TURN] = graph[c].length;
            for (int x : graph[c]) {
                if (x == 0) {
                    children[i][CAT_TURN]--;
                }
            }
            if (m == c && c != 0) {
                states[i][MOUSE_TURN] = CAT_WINS;
                states[i][CAT_TURN] = CAT_WINS;
                q.add(new int[]{i, MOUSE_TURN, CAT_WINS});
                q.add(new int[]{i, CAT_TURN, CAT_WINS});
            }
            if (m == 0) {
                states[i][MOUSE_TURN] = MOUSE_WINS;
                states[i][CAT_TURN] = MOUSE_WINS;
                q.add(new int[]{i, MOUSE_TURN, MOUSE_WINS});
                q.add(new int[]{i, CAT_TURN, MOUSE_WINS});
            }
        }

        while (!q.isEmpty()) {
            int [] node = q.remove();
            int s1 = node[0], t1 = node[1], outcome = node[2];
            int t2 = t1 == CAT_TURN ? MOUSE_TURN : CAT_TURN;
            int m1 = s1 / n, c1 = s1 % n;
            for (int parent : t2 == CAT_TURN ? graph[c1] : graph[m1]) {
                if (t2 == CAT_TURN && parent == 0) continue;
                int m2 = t2 == MOUSE_TURN ? parent : m1, c2 = t2 == CAT_TURN ? parent : c1;
                if (states[m2 * n + c2][t2] == DRAW) {
                    if ((t2 == MOUSE_TURN && outcome == MOUSE_WINS) ||
                        (t2 == CAT_TURN && outcome == CAT_WINS)) {
                        states[m2 * n + c2][t2] = outcome;
                        q.add(new int[]{m2 * n + c2, t2, outcome});
                    } else {
                        children[m2 * n + c2][t2]--;
                        if (children[m2 * n + c2][t2] == 0) {
                            states[m2 * n + c2][t2] = t1 == CAT_TURN ? CAT_WINS : MOUSE_WINS;
                            q.add(new int[]{m2 * n + c2, t2, states[m2 * n + c2][t2]});
                        }
                    }
                }
            }

        }
        return states[n + 2][MOUSE_TURN];
    }
}
