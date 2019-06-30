import java.util.*;

/**
 * X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
 *
 * A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
 *
 * Now given a positive number N, how many numbers X from 1 to N are good?
 *
 * Example:
 * Input: 10
 * Output: 4
 * Explanation:
 * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
 * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
 * Note:
 *
 * N  will be in range [1, 10000].
 */
public class RotatedDigits {
    final static int [] rules = new int[] {1, 1, 2, 0, 0, 2, 2, 0, 1, 2};

    public int rotatedDigits(int N) {
        int [] valid = new int[N + 1];
        int sum = 0, prefix = 0;
        for (int i = 0; i <= N; i++) {
            if (i < 10) valid[i] = rules[i];
            else {
                prefix = i;
                int suffix = prefix % 10;
                prefix /= 10;
                if (valid[prefix] < 1 || rules[suffix] < 1) continue;
                else if (valid[prefix] == 2 || rules[suffix] == 2) valid[i] = 2;
                else if (valid[prefix] == 1 && rules[suffix] == 1) valid[i] = 1;
            }
            if (valid[i] == 2) sum++;
        }
        return sum;
    }

    public void test() {
        System.out.println(rotatedDigits(1000));
    }
}
