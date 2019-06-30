@SuppressWarnings("unchecked")
public class QueueLL<T> extends SingleLinkedList {
    public void enqueue(T item) {
        this.pushBack(item);
    }
    public T dequeue() {
        return (T) this.popFront();
    }
    public boolean empty() {
        return this.isEmpty();
    }
}
