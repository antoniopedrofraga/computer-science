import java.util.*;
import java.util.stream.IntStream;

/**
 * You are given an integer array A.  From some starting index, you can make a series of jumps.  The (1st, 3rd, 5th, ...) jumps in the series are called odd numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even numbered jumps.
 *
 * You may from index i jump forward to index j (with i < j) in the following way:
 *
 * During odd numbered jumps (ie. jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the smallest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
 * During even numbered jumps (ie. jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the largest possible value.  If there are multiple such indexes j, you can only jump to the smallest such index j.
 * (It may be the case that for some index i, there are no legal jumps.)
 * A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by jumping some number of times (possibly 0 or more than once.)
 *
 * Return the number of good starting indexes.
 *
 * Approach: Go trough the array from the right and build a bst from there.
 * Time complexity: O(n log n)
 * Memory complexity: O(n)
 */
public class OddEvenJump {
    class Jump {
        boolean odd;
        boolean even;

        public Jump(int number, List<Jump> jumps, TreeMap<Integer, Integer> bst) {
            if (bst.containsKey(number)) {
                int index = bst.get(number);
                if (jumps.get(index).odd) this.even = true;
                if (jumps.get(index).even) this.odd = true;
            } else {
                Map.Entry<Integer, Integer> entry = bst.higherEntry(number);
                if (entry != null) {
                    int index = entry.getValue();
                    if (jumps.get(index).even) this.odd = true;
                }
                entry = bst.lowerEntry(number);
                if (entry != null) {
                    int index = entry.getValue();
                    if (jumps.get(index).odd) this.even = true;
                }
            }
        }

        public Jump() {
            this.even = false;
            this.odd = false;
        }
    }

    public int oddEvenJumps(int[] A) {
        if (A.length == 0) return 0;
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        List<Jump> jumps = new ArrayList<>(A.length);
        IntStream.range(0, A.length).forEach(i -> jumps.add(new Jump()));
        Jump last = jumps.get(A.length - 1);
        last.even = last.odd = true;
        int counter = 1;
        bst.put(A[A.length - 1], A.length - 1);
        for (int i = A.length - 2; i >= 0; i--) {
            Jump jump = new Jump(A[i], jumps, bst);
            jumps.set(i, jump);
            if (jump.odd) counter++;
            bst.put(A[i], i);
        }
        return counter;
    }

    public void test() {
        System.out.println(oddEvenJumps(new int[]{10,13,12,14,15}));
        System.out.println(oddEvenJumps(new int[]{2,3,1,1,4}));
        System.out.println(oddEvenJumps(new int[]{5,1,3,4,2}));
    }
}
