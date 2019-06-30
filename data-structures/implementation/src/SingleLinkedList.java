public class SingleLinkedList<T> {
    Node head = null, tail = null;
    private int size = 0;

    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void pushFront(T value) {
        if (size == 0) {
            head = tail = new Node(value, null);
        } else {
            head = new Node(value, head);
        }
        size++;
    }
    public T popFront() {
        if (size == 0) {
            throw new IllegalAccessError();
        }
        T value = head();
        head = head.next;
        size--;
        return value;
    }
    public void pushBack(T value) {
        if (size == 0) {
            head = tail = new Node(value, null);
        } else {
            Node temp = tail;
            tail = new Node(value, null);
            temp.next = tail;
        }
        size++;
    }
    public T popBack() {
        T value = tail();
        return remove(size - 1);
    }

    public T head() {
        return head.value;
    }

    public T tail() {
        return tail.value;
    }

    public T remove(int index) {
        if (index > size - 1) {
            throw new IllegalAccessError();
        } else if (index == 0) {
            return popFront();
        } else if (index == size - 1) {
            return popBack();
        } else {
            return remove(index - 1, head.next, head);
        }
    }

    public T remove(T item) {
        if (item.equals(head.value)) {
            return popFront();
        } else {
            return remove(item, head.next, head);
        }
    }

    public void reverse() {
        if (size <= 1) {
            return;
        }

        reverse(head, head.next);
        head.next = null;

        // swap tail and head
        Node temp = head;
        head = tail;
        tail = temp;
    }

    private void reverse(Node previous, Node current) {
        if (current == null) {
            return;
        }
        Node next = current.next;
        current.next = previous;
        reverse(current, next);
    }

    private T remove(int index, Node current, Node previous) {
        if (index == 0) {
            T value = current.value;
            previous.next = current.next;
            size--;
            return value;
        }
        return remove(index - 1, current.next, current);
    }

    private T remove(T item, Node current, Node previous) {
        if (current == null) {
            return null;
        } else if (item.equals(current.value)) {
            T value = current.value;
            previous.next = current.next;
            size--;
            return value;
        }
        return remove(item, current.next, current);
    }

    protected class Node {
        Node next;
        T value;
        Node(T value, Node next) {
            this.next = next;
            this.value = value;
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");
        Node node = head;
        while (node != null) {
            stringBuilder.append(node.value);
            stringBuilder.append(' ');
            node = node.next;
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
