/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * Time complexity: O(n)
 * Memory complexity: O(1)
 *
 */

public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        char [] mapA = new char[128];
        char [] mapB = new char[128];
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (mapA[a] == 0) {
                if (mapB[b] != 0) return false;
                mapA[a] = b;
                mapB[b] = a;
            } else if (mapA[a] != b || mapB[b] != a) {
                return false;
            }
        }
        return true;
    }

}
