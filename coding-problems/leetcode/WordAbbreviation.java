import java.util.*;

/**
 * Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.
 *
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 *
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 *
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
 *
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 *
 * Example:
 *
 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 *
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * Note:
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 *
 * Approach use a trie to insert words, and update them as we go.
 *
 */
public class WordAbbreviation {
    public List<String> wordsAbbreviation(List<String> words) {
        Map<String, List<IndexedWord>> groups = new HashMap<>();
        String[] ans = new String[words.size()];

        int index = 0;
        for (String word: words) {
            String ab = abbrev(word, 0);
            if (!groups.containsKey(ab))
                groups.put(ab, new ArrayList<>());
            groups.get(ab).add(new IndexedWord(word, index));
            index++;
        }

        for (List<IndexedWord> group: groups.values()) {
            TrieNode trie = new TrieNode();
            for (IndexedWord iw: group) {
                TrieNode cur = trie;
                for (char letter: iw.word.substring(1).toCharArray()) {
                    if (cur.children[letter - 'a'] == null)
                        cur.children[letter - 'a'] = new TrieNode();
                    cur.count++;
                    cur = cur.children[letter - 'a'];
                }
            }

            for (IndexedWord iw: group) {
                TrieNode cur = trie;
                int i = 1;
                for (char letter: iw.word.substring(1).toCharArray()) {
                    if (cur.count == 1) break;
                    cur = cur.children[letter - 'a'];
                    i++;
                }
                ans[iw.index] = abbrev(iw.word, i-1);
            }
        }

        return Arrays.asList(ans);
    }

    public String abbrev(String word, int i) {
        int N = word.length();
        if (N - i <= 3) return word;
        return word.substring(0, i+1) + (N - i - 2) + word.charAt(N-1);
    }

    class TrieNode {
        TrieNode[] children;
        int count;
        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }
    class IndexedWord {
        String word;
        int index;
        IndexedWord(String w, int i) {
            word = w;
            index = i;
        }
    }

    public void test() {
        System.out.println(wordsAbbreviation(Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion")).toString());
    }
}
