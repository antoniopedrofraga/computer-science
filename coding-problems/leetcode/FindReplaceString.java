import java.util.*;

/**
 * To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).
 *
 * Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.
 *
 * For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".
 *
 * Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.
 *
 * All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.
 *
 * Example 1:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
 * Output: "eeebffff"
 * Explanation: "a" starts at index 0 in S, so it's replaced by "eee".
 * "cd" starts at index 2 in S, so it's replaced by "ffff".
 * Example 2:
 *
 * Input: S = "abcd", indexes = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation: "ab" starts at index 0 in S, so it's replaced by "eee".
 * "ec" doesn't starts at index 2 in the original S, so we do nothing.
 * Notes:
 *
 * 0 <= indexes.length = sources.length = targets.length <= 100
 * 0 < indexes[i] < S.length <= 1000
 * All characters in given inputs are lowercase letters.
 *
 *
 * Approach: Use a technique called piece table. We record all the valid operations first and put them into a piece table, then iterate the string index to "apply" these operations.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 *
 * Approach: Use a technique called piece table. We record all the valid operations first and put them into a piece table, then iterate the string index to "apply" these operations.
 */
public class FindReplaceString {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> pieceTable = new HashMap<>();

        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            String source = sources[i];
            if (S.substring(index, index + source.length()).equals(source)) {
                pieceTable.put(index, i);
            }
        }

        for (int i = 0; i < S.length();) {
            if (pieceTable.containsKey(i)) {
                result.append(targets[pieceTable.get(i)]);
                i += sources[pieceTable.get(i)].length();
            } else {
                result.append(S.charAt(i));
                i++;
            }
        }
        return result.toString();
    }


    public void test() {
        System.out.println(
                findReplaceString("abcd", new int[]{0,2}, new String[]{"a","cd"}, new String[]{"eee","ffff"})
        );
        System.out.println(
                findReplaceString("abcd", new int[]{0,2}, new String[]{"ab","ec"}, new String[]{"eee","ffff"})
        );
        System.out.println(
                findReplaceString("vmokgggqzp", new int[]{3,5,1}, new String[]{"kg","ggq","mo"}, new String[]{"s","so","bfr"})
        );
        System.out.println(
                findReplaceString("mhnbzxkwzxtaanmhtoirxheyanoplbvjrovzudznmetkkxrdmr", new int[]{46,29,2,44,31,26,42,9,38,23,36,12,16,7,33,18}, new String[]{"rym","kv","nbzxu","vx","js","tp","tc","jta","zqm","ya","uz","avm","tz","wn","yv","ird"}, new String[]{"gfhc","uq","dntkw","wql","s","dmp","jqi","fp","hs","aqz","ix","jag","n","l","y","zww"})
        );
    }
}
