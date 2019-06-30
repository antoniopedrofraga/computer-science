/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Approach: Check whether the next number is equal to this number or not, if it is, then delete.
 * Time complexity: O(n)
 * Memory Complexity: O(1)
 *
 */

public class RemoveDuplicates3 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { this.val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
