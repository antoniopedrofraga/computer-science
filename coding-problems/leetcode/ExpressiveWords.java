/**
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 *
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 *
 * Given a list of query words, return the number of words that are stretchy.
 *
 *
 *
 * Example:
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 *
 * Notes:
 *
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 *
 * Memory complexity: O(1)
 * Time complexity: O(w), with w being the total length of all words
 */

public class ExpressiveWords {
    private boolean invalidExt(int length1, int length2) {
        if (length1 > length2) return true;
        return length1 < length2 && length2 < 3;
    }
    public int expressiveWords(String S, String[] words) {
        int sum = 0;
        for (String word : words) {
            int i = 0, j = 0;
            while (i < word.length() || j < S.length()) {
                int lastI = i, lastJ = j;
                boolean valid = true;
                Character c = null;
                for (; i < word.length(); i++) {
                    c = word.charAt(i);
                    if (i + 1 >= word.length() || word.charAt(i + 1) != word.charAt(i)) {
                        i++;
                        break;
                    }
                }
                for (; j < S.length(); j++) {
                    if (c == null || S.charAt(j) != c) {
                        valid = false;
                        break;
                    }
                    if (j + 1 >= S.length() || S.charAt(j + 1) != S.charAt(j)) {
                        j++;
                        break;
                    }
                }
                if (!valid || invalidExt(i - lastI, j - lastJ)) break;
                if (i == word.length() && j == S.length()) sum++;
            }
        }
        return sum;
    }
    public void test() {
        System.out.println(expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
    }
}
