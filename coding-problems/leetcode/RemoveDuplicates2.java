/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * Approach: Check whether the next number is equal to this number or not, if it is, then delete.
 * Time complexity: O(n)
 * Memory Complexity: O(1)
 *
 */

public class RemoveDuplicates2 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { this.val = x; }
    }
    /**
     * previous = 3
     * Dummy -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        int previous = current.val;
        boolean repeating = false;
        while (current.next != null) {
            if (repeating && current.next.val == previous) {
                current.next = current.next.next;
            } else if (current.next.next != null && current.next.val == current.next.next.val) {
                repeating = true;
                previous = current.next.val;
                current.next = current.next.next.next;
            } else {
                repeating = false;
                current = current.next;
                previous = current.val;
            }
        }
        return dummy;
    }
}
