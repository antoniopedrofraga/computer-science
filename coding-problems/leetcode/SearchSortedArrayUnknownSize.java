import java.util.Arrays;

/**
 * A dummy description of SearchSortedArrayUnknownSize
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
public class SearchSortedArrayUnknownSize {
    class ArrayReader {
        int arr [] = new int[100];
        public ArrayReader(int [] arr) {
            Arrays.fill(this.arr, Integer.MAX_VALUE);
            for (int i = 0; i < arr.length; i++) {
                this.arr[i] = arr[i];
            }
        }
        int get(int i) {
            return arr[i];
        }
    }
    private static final int OUT = Integer.MAX_VALUE;
    public int search(ArrayReader reader, int target) {
        int h = 0, l = 0;
        while (!(reader.get(h) != OUT && reader.get(h + 1) == OUT)) {
            int size = 1;
            while (reader.get(h + size) != OUT) {
                size <<= 1;
            }
            h = h + (size >> 1);
        }
        while (l <= h) {
            int mid = (h - l) / 2 + l;
            if (reader.get(mid) < target) {
                l = mid + 1;
            } else if (reader.get(mid) > target) {
                h = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public void test() {
        System.out.println(search(new ArrayReader(new int[]{1, 2, 3, 4, 5, 6}), 5));
        System.out.println(search(new ArrayReader(new int[]{5}), 5));
    }
}
