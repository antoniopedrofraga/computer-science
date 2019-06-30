/**
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 * We can make the memory complexity to be O(1), if somehow we count the number of nines (counting from the end). We can reverse the list in O(n) and O(1) for example. And them apply the same strategy iteratively.
 */
public class PlusOneLinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode plusOne(ListNode head) {
        int carry = recursivePlus(head);
        if (carry > 0) {
            ListNode temp = head;
            head = new ListNode(carry);
            head.next = temp;
        }
        return head;
    }

    public int recursivePlus(ListNode head) {
        int carry;
        if (head.next != null) {
            carry = recursivePlus(head.next) + head.val;
        } else {
            carry = 1 + head.val;
        }
        head.val = carry % 10;
        carry /= 10;
        return carry;
    }
}
