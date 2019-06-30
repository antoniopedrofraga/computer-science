import java.util.LinkedList;
import java.util.Queue;

/**
 * Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.
 *
 * Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
 *
 * Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.
 *
 * Examples:
 *
 * Input: "WRRBBW", "RB"
 * Output: -1
 * Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW
 *
 * Input: "WWRRBBWW", "WRBRW"
 * Output: 2
 * Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty
 *
 * Input:"G", "GGGGG"
 * Output: 2
 * Explanation: G -> G[G] -> GG[G] -> empty
 *
 * Input: "RBYYBBRRB", "YRBGB"
 * Output: 3
 * Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty
 *
 * Note:
 * You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
 * The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
 * The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
 * Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.
 *
 *
 *
 * Approach: This is an unweighted graph problem, use bfs to find the minimum path.
 * Time complexity: Exponential
 * Memory complexity: Exponential
 */

public class ZumaGame {
class Node {
    String board;
    String hand;
    int level;
    public Node(String board, String hand, int level) {
        this.board = board;
        this.hand = hand;
        this.level = level;
    }
}
public int findMinStep(String board, String hand) {
    Queue<Node> bfs = new LinkedList<>();
    bfs.add(new Node(board, hand, 0));
    while (!bfs.isEmpty()) {
        Node top = bfs.remove();
        StringBuilder handBuilder = new StringBuilder(top.hand);
        StringBuilder boardBuilder = processBoard(top);

        if (boardBuilder.length() == 0) {
            return top.level;
        }
        if (handBuilder.length() == 0 && boardBuilder.length() > 0 && top.level > 5) {
            break;
        }

        for (int i = 0; i < handBuilder.length(); i++) {
            char c = handBuilder.charAt(i);
            for (int j = 0; j < boardBuilder.length(); j++) {
                if (c == boardBuilder.charAt(j) && (j == 0 || boardBuilder.charAt(j - 1) != c)) {
                    boardBuilder.insert(j, c);
                    handBuilder.deleteCharAt(i);
                    bfs.add(new Node(boardBuilder.toString(), handBuilder.toString(), top.level + 1));
                    boardBuilder.deleteCharAt(j);
                    handBuilder.insert(i, c);
                }
            }
        }
    }
    return -1;
}

StringBuilder processBoard(Node node) {
    String localBoard = node.board;
    StringBuilder boardBuilder = new StringBuilder();
    boolean removed = true;
    while (removed && localBoard.length() > 0) {
        removed = false;
        boardBuilder.setLength(0);
        int i = 0, j = 1;
        for (; j < localBoard.length(); j++) {
            if (localBoard.charAt(j) != localBoard.charAt(j - 1)) {
                if (j - i < 3) {
                    boardBuilder.append(localBoard, i, j);
                } else {
                    removed = true;
                }
                i = j;
            }
        }
        if (j - i < 3) {
            boardBuilder.append(localBoard, i, j);
        } else {
            removed = true;
        }
        localBoard = boardBuilder.toString();
    }
    return boardBuilder;
}
    public void test() {
        System.out.println(findMinStep("WRRBBW", "RB"));
        System.out.println(findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(findMinStep("G", "GGGGG"));
        System.out.println(findMinStep("RBYYBBRRB", "YRBGB"));
        System.out.println(findMinStep("RWYWRRWRR", "YRY"));
    }
}
