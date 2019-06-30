import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of words (without duplicates), find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * Example 2:
 *
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 *
 * Time complexity: Exponential
 * Memory complexity: Exponential
 */
public class WordSquares {
    class TrieNode {
        boolean end = false;
        TrieNode [] childs = new TrieNode[26];
        public void insert(String string, int i) {
            if (i >= 0) {
                char c = string.charAt(string.length() - 1 - i);
                if (childs[c - 'a'] == null) {
                    childs[c - 'a'] = new TrieNode();
                }
                childs[c - 'a'].insert(string, i - 1);
            } else {
                end = true;
            }
        }
        private List<String> getCandidates(StringBuilder prefix, int i) {
            if (i < prefix.length()) {
                char c = prefix.charAt(i);
                return childs[c - 'a'] != null ? childs[c - 'a'].getCandidates(prefix, i + 1) : Collections.emptyList();
            }
            List<String> result = new ArrayList<>();
            if (end) {
                result.add(prefix.toString());
            }
            for (int j = 0; j < childs.length; j++) {
                if (childs[j] != null) {
                    prefix.append((char)(j + 'a'));
                    result.addAll(childs[j].getCandidates(prefix, i + 1));
                    prefix.setLength(prefix.length() - 1);
                }
            }
            return result;
        }
    }

    public List<List<String>> getSquares(TrieNode trie, List<char[]> current, TrieNode [] nodes, int i) {
        List<List<String>> result = new ArrayList<>();
        TrieNode [] copy = new TrieNode[nodes.length];
        for (int j = 0; j < current.size(); j++) {
            char c = current.get(j)[i];
            if (nodes[j].childs[c - 'a'] == null) {
                return Collections.emptyList();
            } else {
                copy[j] = nodes[j].childs[c - 'a'];
            }
        }
        if (i + 1 == current.size()) {
            List<String> square = new ArrayList<>();
            for (char [] string : current) {
                square.add(new String(string));
            }
            result.add(square);
            return result;
        }
        StringBuilder prefix = new StringBuilder();
        for (int j = 0; j < i + 1; j++) {
            prefix.append(current.get(i + 1)[j]);
        }
        List<String> candidates = trie.getCandidates(prefix, 0);
        for (String candidate : candidates) {
            for (int j = 0; j < candidate.length(); j++) {
                current.get(j)[i + 1] = candidate.charAt(j);
            }
            result.addAll(getSquares(trie, current, copy, i + 1));
        }
        return result;
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        List<char []> current = new ArrayList<>();
        TrieNode [] nodes = new TrieNode[words[0].length()];
        TrieNode trie = new TrieNode();
        for (int i = 0; i < words[0].length(); i++) {
            current.add(new char[words[0].length()]);
        }
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], words[i].length() - 1);
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                current.get(j)[0] = words[i].charAt(j);
                nodes[j] = trie;
            }
            List<List<String>> squares = getSquares(trie, current, nodes,0);
            if (squares != null) {
                result.addAll(squares);
            }
        }
        return result;
    }
    public void test() {
        List<List<String>> result1 = wordSquares(new String[]{"area","lead","wall","lady","ball"});
        for (List<String> strings : result1) {
            System.out.println(strings.toString());
        }
    }
}
