import java.util.EmptyStackException;

public class Stack<T> extends ResizableArray {

    public boolean empty() {
        return this.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        int size = this.size();
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (T) this.get(size - 1);
    }

    @SuppressWarnings("unchecked")
    public void push(Comparable item) {
        this.pushBack(item);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{ ");
        for (int i = 0; i < size(); i++) {
            stringBuilder.append(this.get(i).toString());
            stringBuilder.append(' ');
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
