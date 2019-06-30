import java.util.*;

/**
 * Given a 1-d array candy crush, return the shortest array after removing all the continuous same numbers (the repeating number >= 3)
 * input: 1-d array [1, 3, 3, 3, 2, 2, 2, 3, 1]
 * return: [1, 1]
 * Time complexity should be better than O(n^2)
 */
public class RemoveRepeatingNumbers {
    public int[] removeRepeatingNumbers(int[] arr) {
        int[] result = new int[arr.length];
        Map<Integer, Integer> freq = new HashMap<>();
        int length = 0;
        for (int n : arr) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        for (int i = 0; i < arr.length; i++) {
            if (freq.get(arr[i]) < 3) {
                result[length++] = arr[i];
            }
        }
        return Arrays.copyOf(result, length);
    }
}
