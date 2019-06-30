public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int value = 0;
            if (l1 != null) { value += l1.val; l1 = l1.next; }
            if (l2 != null) { value += l2.val; l2 = l2.next; }
            node.next = new ListNode((value + carry) % 10);
            carry = (value + carry) / 10;
            node = node.next;
        }
        return dummy.next;
    }

    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }
}
