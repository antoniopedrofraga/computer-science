import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 3
 * Output: [1,3,3,1]
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangle {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> kth = new ArrayList<>(rowIndex);
        kth.add(1);
        for (int k = 0; k < rowIndex; k++) {
            kth.add(1);
            int last = 1;
            for (int i = 1; i < kth.size() - 1; i++) {
                int newLast = kth.get(i);
                kth.set(i, newLast + last);
                last = newLast;
            }
        }
        return kth;
    }

    public void test() {
        System.out.println(getRow(3).toString());
        System.out.println(getRow(4).toString());
    }
}
