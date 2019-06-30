import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A[k] * 2 = A[i] + A[j]
 *
 * i < k < j
 *
 * [1]
 *
 * [1, 2]
 * [1, 3, 2, 4]
 * [1, 5, 3, 7, 2, 10, 4, 8]
 *
 * [3,1,2,4,5]
 *
 * Approach: Let’s start by stating that for any addition or multiplication, we can still obtain a beautiful array.
 *
 * Addition: With our condition being “A[k] * 2 != A[i] + A[j]”, we have that for any x, “(A[k] + x) * 2 != A[i] + x + A[j] + x”
 *
 * Multiplication: Similarly, we can state the same for a multiplication. For any x != 0, we have that “(A[k] * x) * 2 != A[i] * x + A[j] * x”
 *
 * Therefore, having an initial beautiful array A, we can form a new beautiful array by always separating the odd and even numbers (A1 and A2). One of them will be included in the first part of the array, and the other in the second part of the array.
 *
 *
 * Memory complexity: O(n)
 * Time complexity: O(n)
 */
public class BeautifulArray {
    public int[] beautifulArray(int N) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        while (list.size() < N) {
            List<Integer> temp = new ArrayList<>(list.size() * 2 );
            for (int i : list) {
                if (i * 2 - 1 <= N) temp.add(i * 2 - 1);
            }
            for (int i : list) {
                if (i * 2 <= N) temp.add(i * 2);
            }
            list = temp;
        }
        return list.parallelStream().mapToInt(i -> i).toArray();
    }
}
