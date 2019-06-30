/**
 * A dummy description of ReverseLinkedListII
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class ReverseLinkedListII {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int i = 0;
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        ListNode pivot = new ListNode(-1), previous = dummy;
        dummy.next = head;
        while (current != null) {
            ListNode previousTemp = current;
            if (i + 1 == m) {
                pivot = current;
                current = current.next;
            } else if (i > m && i < n) {
                ListNode temp = current.next;
                current.next = previous;
                current = temp;
            } else if (i == n) {
                ListNode temp = pivot.next;
                ListNode temp2 = current.next;
                pivot.next = current;
                current.next = previous;
                temp.next = temp2;
                current = temp2;
            } else {
                current = current.next;
            }
            i++;
            previous = previousTemp;
        }
        return dummy.next;
    }

    public ListNode buildList(int [] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int i = 0; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            current.next = node;
            current = node;
        }
        return dummy.next;
    }

    public void print(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("NULL");
    }

}
