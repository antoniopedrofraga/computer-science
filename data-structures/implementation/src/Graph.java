import java.util.*;
import java.util.Stack;

public class Graph<T extends Comparable> {
    Node root;
    class Node {
        T value;
        ArrayList<Node> children;
    }
    public void breadthFirstSearch(Node node) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>(Arrays.asList(node));
        while (!queue.isEmpty()) {
            Node parent = queue.remove();
            for (Node child : parent.children) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                }
            }
        }
    }

    public void depthFirstSearch(Node node) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            Node parent = stack.pop();
            for (Node child : parent.children) {
                if (!visited.contains(child)) {
                    visited.add(child);
                    stack.push(child);
                }
            }
        }
    }
}
