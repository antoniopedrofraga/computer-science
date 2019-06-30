/**
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 * Input: 27
 * Output: true
 *
 * Example 2:
 * Input: 0
 * Output: false
 *
 * Example 3:
 * Input: 9
 * Output: true
 *
 * Example 4:
 * Input: 45
 * Output: false
 *
 * Follow up:
 * Could you do it without using any loop / recursion?
 *
 * Approach: Successively divide by three until we have one.
 * Time complexity: O(1) We’re dealing with 32 bits
 * Memory complexity: O(1)
 */
public class IsPowerOf3 {
    public boolean isPowerOfThree(int n) {
        StringBuilder builder = new StringBuilder();
        if (n < 1) return false;
        while (n % 3 == 0) n /= 3;
        return n == 1;
    }
}
