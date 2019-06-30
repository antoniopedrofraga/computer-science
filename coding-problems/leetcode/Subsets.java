import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 *
 * Idea: Implement a BFS by using a queue (avoiding recursion).
 * Time complexity: 2^n
 * Space Complexity: n * 2^n
 */

public class Subsets {
    class Node {
        int index;
        List<Integer> list;
        public Node(int index, List<Integer> list) {
            this.index = index;
            this.list = new LinkedList<>(list);
        }
    }
    public List<List<Integer>> subsets(int [] num) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, new LinkedList<>()));
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int index = node.index;
            List<Integer> list = node.list;
            if (index < num.length) {
                queue.add(new Node(index + 1, list));
                list.add(num[index]);
                queue.add(new Node(index + 1, list));
            } else {
                result.add(list);
            }
        }
        return result;
    }
}
