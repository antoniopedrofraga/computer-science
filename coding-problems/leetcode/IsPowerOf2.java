/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 * Input: 1
 * Output: true
 * Explanation: 20 = 1
 *
 * Example 2:
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 *
 * Example 3:
 * Input: 218
 * Output: false
 *
 * Approach: If number is less or equal than 0, then return false, else shift while the most right bit is == 0. Then return number == 1.
 *
 * Time complexity: O(1)
 * Memory complexity: O(1)
 */
public class IsPowerOf2 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while ((n & 1) == 0) n >>= 1;
        return n == 1;
    }
}
