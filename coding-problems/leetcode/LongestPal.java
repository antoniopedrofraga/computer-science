/**
 * TO-DO - solve it with a space complexity of O(1)
 * The dp approach takes O(n square)
 */

public class LongestPal {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }

        boolean [][] pal = new boolean[s.length()][s.length()];
        String longest = s.charAt(0) + "";

        for (int i = 0; i < s.length(); i++) {
            pal[i][i] = true;
            if (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                pal[i][i + 1] = true;
                longest = s.substring(i, i + 2);
            }
        }
        for (int length = 3; length <= s.length(); length++) {
            for (int i = 0; i < s.length() - length + 1; i++) {
                int j = i + length - 1;
                if (pal[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    pal[i][j] = true;
                    if (length > longest.length()) {
                        longest = s.substring(i, j + 1);
                    }
                }
            }
        }
        return longest;
    }
}
