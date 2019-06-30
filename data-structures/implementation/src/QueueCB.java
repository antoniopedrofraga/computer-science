import java.util.NoSuchElementException;

public class QueueCB<T> {

    private static int DEFAULT_SIZE = 20;

    private int size = DEFAULT_SIZE;

    private int head = 0;

    private int tail = 0;

    Object [] buffer;

    QueueCB() {
        this.buffer = new Object[size];
    }

    QueueCB(int size) {
        if (size < 2) {
            throw new IllegalArgumentException();
        }
        this.size = size;
        this.buffer = new Object[size];
    }

    private int incrementPointer(int pointer) {
        if (pointer == size - 1) {
            return 0;
        }
        return pointer + 1;
    }

    public void enqueue(T item) {
        if (full()) {
            throw new OutOfMemoryError();
        }
        buffer[tail] = item;
        tail = incrementPointer(tail);
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        T temp = (T) buffer[head];
        buffer[head] = null; // dereference to let gc do the work
        head = incrementPointer(head);
        return temp;
    }

    public boolean empty() {
        return head == tail;
    }

    public boolean full() {
        return  incrementPointer(tail) == head;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");
        for (int i = head; i != tail; i = incrementPointer(i)) {
            stringBuilder.append(buffer[i].toString());
            stringBuilder.append(' ');
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
