import javafx.util.Pair;

import java.util.*;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells. “Adjacent” cells are those horizontally or vertical neighboring. The same letter cell may not be used more than once.
 *
 * Idea: Run a DFS on nodes that match the description.
 */

public class WordSearch {
    private static final List<Pair<Integer, Integer>> moves = Arrays.asList(new Pair<>(0, -1), new Pair<>(0, 1), new Pair<>(-1, 0), new Pair<>(1, 0));
    class Node {
        int position;
        int index;
        Set<Integer> visited;
        public Node(int index, int position, Set<Integer> visited) {
            this.index = index;
            this.position = position;
            this.visited = new HashSet<>(visited);
            this.visited.add(position);
        }
    }
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        if (rows == 0) return false;
        int cols = board[0].length;

        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            dp.add(new HashSet<>());
        }
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == word.charAt(0)) {
                    int position = i * cols + j;
                    stack.push(new Node(0, position, new HashSet<>()));
                }
            }
        }

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            int index = node.index + 1;
            int y = node.position / cols,
                    x = node.position % cols;
            Set<Integer> visited = node.visited;
            if (index >= word.length()) return true;
            for (Pair<Integer, Integer> move : moves) {
                int newY = y + move.getKey(),
                        newX = x + move.getValue();
                int position = newY * cols + newX;
                if (newY < 0 || newY >= rows) continue;
                if (newX < 0 || newX >= cols) continue;
                if (visited.contains(position)) continue;
                if (board[newY][newX] == word.charAt(index)) {
                    stack.push(new Node(index, position, visited));
                }
            }
        }
        return false;
    }


}
