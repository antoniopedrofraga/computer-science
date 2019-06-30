import java.util.Arrays;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 *
 * Approach: Sort by count and distribute characters
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class ReorganizeString {
    class Letter implements Comparable<Letter> {
        char c;
        int nr;
        public Letter(char c) {
            this.c = c;
            this.nr = 0;
        }
        @Override
        public int compareTo(Letter l) {
            return l.nr - this.nr;
        }
    }

    public String reorganizeString(String S) {
        Letter [] letters = new Letter[26];
        char [] result = new char[S.length()];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = new Letter((char)('a' + i));
        }
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int index = (int) (c - 'a');
            letters[index].nr++;
        }
        Arrays.sort(letters);
        int chunks = (int) Math.ceil(S.length() / (double) letters[0].nr);
        int i = 0, j = 0;
        for (Letter letter : letters) {
            if (letter.nr == 0) break;
            while (letter.nr > 0) {
                char c = letter.c;
                if (i >= result.length) i = ++j;
                result[i] = c;
                if (i != 0 && c == result[i - 1]) return "";
                i += chunks;
                letter.nr--;
            }
        }
        return new String(result);
    }

    public void test() {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
    }
}
