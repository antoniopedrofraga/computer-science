import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *
 * Return a deep copy of the list.
 *
 * Example 1:
 *
 * Input:
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * Explanation:
 * Node 1's value is 1, both of its next and random pointer points to Node 2.
 * Node 2's value is 2, its next pointer points to null and its random pointer points to itself.
 *
 * Note:
 * You must return the copy of the given head as a reference to the cloned list.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class CopyRandomList {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        return dfs(new HashMap<>(), head);
    }
    private Node dfs(Map<Integer, Node> nodes, Node node) {
        Node copy = new Node();
        copy.val = node.val;
        nodes.put(copy.val, copy);
        if (node.next != null) {
            int val = node.next.val;
            copy.next = nodes.containsKey(val) ? nodes.get(val) : dfs(nodes, node.next);
        }
        if (node.random != null) {
            int val = node.random.val;
            copy.random = nodes.containsKey(val) ? nodes.get(val) : dfs(nodes, node.random);
        }
        return copy;
    }
}
