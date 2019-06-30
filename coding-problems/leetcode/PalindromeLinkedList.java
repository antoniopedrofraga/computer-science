/**
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public boolean isPalindrome(ListNode head) {
        int size = -1, mid = 0;
        ListNode node = head, last = head;
        while (node != null) {
            size++;
            node = node.next;
        }
        node = head;
        mid = size / 2;
        for (int i = 0; i <= size; i++) {
            if (i < mid) {
                node = node.next;
                last = node;
            } else {
                ListNode temp = node.next;
                node.next = last;
                last = node;
                node = temp;
            }
        }
        int i = 0, j = size;
        while (i < j) {
            if (head.val != last.val) return false;
            head = head.next;
            last = last.next;
            i++; j--;
        }
        return true;
    }
}
