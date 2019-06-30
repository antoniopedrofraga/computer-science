/**
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 *
 * Time Complexity: O(n * m)
 * Memory complexity: O(m)
 */
public class CountAndSay {
    public String countAndSay(int n) {
        String state = "1";
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < n; i++) {
            builder.setLength(0);
            char c = state.charAt(0);
            int size = 1;
            for (int j = 1; j < state.length(); j++) {
                if (c != state.charAt(j)) {
                    builder.append(size);
                    builder.append(c);
                    c = state.charAt(j);
                    size = 0;
                }
                size++;
            }
            builder.append(size);
            builder.append(c);
            state = builder.toString();
        }
        return state;
    }
    public void test() {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
    }
}
