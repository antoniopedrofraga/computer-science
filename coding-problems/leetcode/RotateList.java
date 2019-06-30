public class RotateList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    int getListSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }
    public ListNode rotateRight(ListNode head, int k) {
        int size = getListSize(head);
        if (size == 0) {
            return null;
        }
        k = k % size;
        if (k == 0 || size == 1 || k == size) {
            return head;
        }
        int ith = size - k - 1;
        ListNode pointer = head, nextHead = null;
        for (int i = 0; i < ith; i++) {
            pointer = pointer.next;
        }
        nextHead = pointer.next;
        pointer.next = null;
        pointer = nextHead;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        pointer.next = head;
        return nextHead;
    }
}
