/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Example 1:
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 *
 * Example 2:
 *
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 *
 * Example 3:
 *
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 *
 * Follow up:
 *
 * Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycle {
    class ListNode {
        int val;
        ListNode next;
            ListNode(int x) {
                val = x;
                next = null;
            }
    }
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        int value = 0;
        while (fast != null) {
            if (value == 1) {
                slow = slow.next;
            }
            fast = fast.next;
            if (fast == slow) return true;
            value = (value + 1) % 2;
        }
        return false;
    }
}
