import java.util.Comparator;

@SuppressWarnings({"unchecked", "WeakerAccess", "unused"})
public class Heap<T extends Comparable<T>> {

    private final static int UNDEFINED = 0;

    protected int heapSize;

    protected ResizableArray<T> array;

    protected Comparator<? super T> comparator;

    public Heap() {
        this(UNDEFINED, null);
    }

    public Heap(T [] array) {
        this.array = new ResizableArray<T>(array.length);
        heapSize = array.length;
        this.array.size = heapSize;
        System.arraycopy(array, 0, this.array.array, 0, array.length);
        this.comparator = Comparator.naturalOrder();
        buildHeap();
    }

    public Heap(int size) {
        this(size, null);
    }

    public Heap(Comparator<? super T> comparator) {
        this(UNDEFINED, null);
    }

    public Heap(int size,
                Comparator<? super T> comparator) {
        if (size < 0) throw new IllegalArgumentException();
        heapSize = size;
        this.array = size == UNDEFINED ? new ResizableArray<>() : new ResizableArray<>(size);
        this.comparator = comparator != null ? comparator :
                Comparator.naturalOrder();
    }

    public void setSize(int size) {
        heapSize = size;
    }

    protected void heapify(int index) {
        int size = heapSize;
        if (size <= index) { throw new IllegalArgumentException(); }
        T temp =  this.array.get(index);
        boolean validHeap = false;
        while (index < heapSize / 2 && !validHeap) {
            int left = left(index), right = right(index);
            if (left < heapSize && right < heapSize) {
                int max = comparator.compare(this.array.get(right), this.array.get(left)) < 0 ? left : right;
                this.array.swap(index, max);
                index = max;
            } else if (left < heapSize && comparator.compare(this.array.get(index), this.array.get(left)) < 0) {
                this.array.swap(index, left);
                index = left;
            } else if (right < heapSize && comparator.compare(this.array.get(index), this.array.get(right)) < 0) {
                this.array.swap(index, right);
                index = right;
            } else {
                validHeap = true;
            }
        }
    }

    protected void buildHeap() {
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    public int size() {
        return heapSize;
    }
    protected int right(int index) {
        return 2 * index + 1;
    }
    protected int left(int index) {
        return 2 * index + 2;
    }
    protected int parent(int index) {
        return (index - 1) / 2;
    }
}
