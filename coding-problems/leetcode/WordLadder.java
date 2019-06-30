import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

public class WordLadder {
    class Word {
        boolean visited = false;
        List<String> childs = new ArrayList<>();
        public Word() {}
    }
    boolean isChild(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
            if (count > 1) return false;
        }
        return count == 1;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, Word> words = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        if (!wordList.contains(beginWord)) {
            Word word = new Word();
            words.put(beginWord, word);
            for (int j = 0; j < wordList.size(); j++) {
                if (isChild(beginWord, wordList.get(j))) {
                    word.childs.add(wordList.get(j));
                }
            }
        }
        for (int i = 0; i < wordList.size(); i++) {
            Word word = new Word();
            words.put(wordList.get(i), word);
            for (int j = 0; j < wordList.size(); j++) {
                if (i == j) continue;
                if (isChild(wordList.get(i), wordList.get(j))) {
                    word.childs.add(wordList.get(j));
                }
            }
        }
        levels.add(1);
        q.add(beginWord);
        while (!q.isEmpty()) {
            String top = q.remove();
            int level = levels.remove();
            if (top.equals(endWord)) return level;
            Word word = words.get(top);
            word.visited = true;
            for (String child : word.childs) {
                if (!words.get(child).visited) {
                    q.add(child);
                    levels.add(level + 1);
                }
            }
        }
        return 0;
    }
    public void test() {
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log")));
    }
}
