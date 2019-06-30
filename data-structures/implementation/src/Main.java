import java.lang.String;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> al;
        for (Integer integer : heapSort(new Integer[]{3, 1, 2, 6, 4, 2})) {
            System.out.print(integer + " ");
        }
    }

    public static Integer [] heapSort(Integer [] array) {
        Heap<Integer> heap = new Heap<>(array);
        int heapSize = heap.size();
        while (heap.size() > 2) {
            heap.array.swap(0, heapSize - 1);
            heapSize--;
            heap.setSize(heapSize);
            heap.heapify(0);
        }
        if (heapSize == 2) {
            heap.array.swap(0, 1);
        }
        return heap.array.toArray(new Integer[0]);
    }
}
