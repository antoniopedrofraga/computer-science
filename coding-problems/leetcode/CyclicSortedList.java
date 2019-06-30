/**
 *
 * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list.
 * The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
 *
 * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
 *
 */
public class CyclicSortedList {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal, null);
        if (head == null) {
            return node;
        }
        Node tmp = head;
        while (tmp.next != head) {
            if ((tmp.val <= insertVal && insertVal <= tmp.next.val)
                    || (insertVal <= tmp.val && insertVal <= tmp.next.val && tmp.next.val < tmp.val)
                    || (insertVal >= tmp.val && insertVal >= tmp.next.val && tmp.val > tmp.next.val)) {
                node.next = tmp.next;
                tmp.next = node;
                return head;
            }
            tmp = tmp.next;
        }
        node.next = head;
        tmp.next = node;
        return head;
    }

}
