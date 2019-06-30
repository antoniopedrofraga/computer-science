/*
    Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

    You should preserve the original relative order of the nodes in each of the two partitions.

    Example:
    Input: head = 1->4->3->2->5->2, x = 3
    Output: 1->2->2->4->3->5

    Time complexity: O(n)
    Memory Complexity: O(1)
*/

public class PartitionList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode dummyLeft = new ListNode(-1);
        ListNode left = dummyLeft;
        ListNode dummyRight = new ListNode(-1);
        ListNode right = dummyRight;

        while (head != null) {
            if (head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }

        left.next = dummyRight.next;
        right.next = null;
        return dummyLeft.next;
    }

}
