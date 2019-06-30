/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 */
public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;

        ListNode previous = null;
        while (head.next != null) {
            ListNode temp = head.next;
            head.next = previous;
            previous = head;
            head = temp;
        }
        head.next = previous;

        return head;
    }
}
