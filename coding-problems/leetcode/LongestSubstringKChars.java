/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: T is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "aa", k = 1
 * Output: 2
 * Explanation: T is "aa" which its length is 2.
 */
public class LongestSubstringKChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        int [] chars = new int[128];
        int lower = 0, size = 0, num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars[c] == 0) {
                num++;
            }
            chars[c] = i + 1;
            if (num > k) {
                lower = remove(chars);
                num--;
            }
            size = Math.max(i - lower + 1, size);
        }
        return size;
    }

    private int remove(int [] chars) {
        int lower = Integer.MAX_VALUE, rem = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0 && chars[i] < lower) {
                lower = chars[i];
                rem = i;
            }
        }
        chars[rem] = 0;
        return lower;
    }

    public void test() {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));
        System.out.println(lengthOfLongestSubstringKDistinct("ddabc", 3));
    }
}
