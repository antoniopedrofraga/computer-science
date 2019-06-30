public class PriorityQueue<T extends Comparable<T>> extends Heap<T> {
    public T max() throws IllegalAccessException {
        if (this.size() == 0) {
            throw new IllegalAccessException();
        }
        return this.array.get(0);
    }
    public T popMax() throws IllegalAccessException {
        if (this.size() == 0) {
            throw new IllegalAccessException();
        }
        T temp = this.array.get(0);
        this.array.swap(0, heapSize - 1);
        heapSize--;
        heapify(0);
        return temp;
    }
    private void increaseKey(int index, T newKey) {
        if (index >= heapSize) {
            throw new IllegalArgumentException();
        }
        array.array[index] = newKey;
        while (parent(index) >= 0 && comparator.compare(array.get(parent(index)), array.get(index)) < 0) {
            array.swap(parent(index), index);
            index = parent(index);
        }
    }
    public void insert(T key) {
        array.pushBack(null);
        heapSize = array.size();
        increaseKey(heapSize - 1, key);
    }
}
