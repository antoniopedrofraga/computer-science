import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        List<Integer> result = new ArrayList();
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            carry += digits[i];
            int digit = carry % 10;
            carry /= 10;
            result.add(digit);
        }
        while (carry > 0) {
            int digit = carry % 10;
            carry /= 10;
            result.add(digit);
        }
        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}
