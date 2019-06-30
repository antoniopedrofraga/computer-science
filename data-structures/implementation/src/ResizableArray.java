import java.util.Arrays;

public class ResizableArray<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int capacity = DEFAULT_CAPACITY;

    transient Object [] array;

    protected int size = 0;

    ResizableArray(int newCapacity) {
        if (newCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = newCapacity;
        array = new Object[capacity];
    }

    ResizableArray() {
        array = new Object[capacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) array[index];
    }

    public void pushBack(T item) {
        resize();
        array[size++] = item;
    }

    public void insert(T item, int index) {
        tightRangeCheck(index);
        if (index == size) {
            this.pushBack(item);
        } else {
            resize();
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = item;
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (size >= 1) {
            array[size] = null; // gc will do the work
            return (T) array[--size];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void delete(int index) {
        tightRangeCheck(index);
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        array[size] = null; // gc will do the work
        size--;
    }

    // Overflow aware
    private void resize() {
        if (size + 1 < capacity) {
            return;
        }
        int tempValue = (capacity >> 1);
        // Overflow
        if (capacity + tempValue < 0) {
            if (capacity != Integer.MAX_VALUE) {
                capacity = Integer.MAX_VALUE;
            } else {
                throw new OutOfMemoryError();
            }
        } else {
            // Java style
            capacity = capacity + tempValue;
        }
        array = Arrays.copyOf(array, capacity);
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    protected void swap(int index1, int index2) {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void tightRangeCheck(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
