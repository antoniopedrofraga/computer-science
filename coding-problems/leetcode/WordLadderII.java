import java.util.*;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
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
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {
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
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, Word> words = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        int shortest = Integer.MAX_VALUE;

        Queue<String> q = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        Queue<List<String>> lists = new LinkedList<>();

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
        lists.add(new ArrayList<>());
        while (!q.isEmpty()) {
            String top = q.remove();
            List<String> path = lists.remove();
            path.add(top);
            int level = levels.remove();
            if (shortest < path.size()) {
                break;
            }
            if (top.equals(endWord)) {
                shortest = path.size();
                result.add(path);
            }
            Word word = words.get(top);
            word.visited = true;
            for (String child : word.childs) {
                if (!words.get(child).visited) {
                    q.add(child);
                    levels.add(level + 1);
                    lists.add(new ArrayList<>(path));
                }
            }
        }
        return result;
    }
    public void test() {
        System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")).toString());
        System.out.println(findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log")).toString());
    }
}
