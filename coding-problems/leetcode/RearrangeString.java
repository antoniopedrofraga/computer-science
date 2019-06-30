/**
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * Example 2:
 *
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 *
 * Approach: Hold two arrays (one including which valid indexes we have, and one including frequencies).
 * We should choose the maximum frequence with a valid index. If there's none, then we know it is not a valid input.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class RearrangeString {
public String rearrangeString(String s, int k) {
    if (s.length() == 0) return "";
    if (k < 2) return s;
    int [] freq = new int[26];
    int [] left = new int[26];
    char [] result = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
        char c = (char)(s.charAt(i) - 'a');
        freq[c]++;
    }
    for (int i = 0; i < result.length; i++) {
        int j = getValid(freq, left, i);
        if (j == -1) return "";
        result[i] = (char) (j + 'a');
        freq[j]--;
        left[j] = i + k;
    }
    return new String(result);
}

public int getValid(int [] freq, int [] left, int j) {
    int max = 0;
    int candidate = -1;
    for (int i = 0; i < freq.length; i++) {
        if (freq[i] > max && j >= left[i]) {
            candidate = i;
            max = freq[i];
        }
    }
    return candidate;
}

    public void test() {
        String [] ss = new String[]{"aabbcc", "aaabc", "aaadbbcc", "a", "a", "a", "abb", "aaab", "abeabac", "abcdabcdabdeac", "bbabcaccaaabababbaaaaccbbcbacbacacccbbcaabcbcacaaccbabbbbbcacccaccbabaccbacabcabcacb"};
        int [] ks = new int[]{3, 3, 2, 0, 1, 2, 2, 2, 3, 4, 2};
        String [] expected = new String[]{"abcabc", "", "abcabdac", "a", "a", "a", "bab", "", "abcabea", "abcdabcdabcdae", "cacabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc"};

        for (int i = 0; i < ss.length; i++) {
            String result = rearrangeString(ss[i], ks[i]);
            if (!result.equals(expected[i])) {
                System.out.println("s = " + ss[i] + " k = " + ks[i] + " expected \"" + expected[i] + "\" but got \"" + result + "\"");
            }
        }
    }
}
