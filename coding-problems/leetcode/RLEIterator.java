import java.util.Arrays;
import java.util.List;

/**
 * A dummy description of RLEIterator
 *
 * @author Fraga (antoniopedrofraga@gmail.com)
 * @since 1.0
 */
class RLEIterator {
    public RLEIterator() {}
    int i = 0;
    int [] array;
    public RLEIterator(int[] A) {
        array = A;
    }
    public int next(int n) {
        int last = -1;
        while (n > 0 && i < array.length) {
            if (array[i] > 0) {
                n -= array[i];
                array[i] = -n;
                last = array[i + 1];
            } else {
                i += 2;
                last = -1;
            }
        }
        return last;
    }
    public void test() {
        List<Integer> queries = Arrays.asList(5039, 62, 3640, 671, 67, 395, 262, 839, 74, 43, 42, 77, 13, 6, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        RLEIterator rleIterator = new RLEIterator(new int[]{635, 606, 576, 391, 370, 981, 36, 21, 961, 185, 124, 210, 801, 937, 22, 426, 101, 260, 221, 647, 350, 180, 504, 39, 101, 989, 199, 358, 732, 839, 919, 169, 673, 967, 58, 676, 846, 342, 250, 597, 442, 174, 472, 410, 569, 509, 311, 357, 838, 251});
        for (int query : queries) {
            System.out.println(rleIterator.next(query));
        }
    }
}

