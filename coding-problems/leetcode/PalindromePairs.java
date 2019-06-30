import java.util.*;
import java.util.stream.IntStream;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j]is a palindrome.
 *
 * Example 1:
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * Example 2:
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 * Approach: Build a hash map and compare prefixes and sufixes.
 * Time complexity: O(n k^2)
 * Memory complexity: O(n + k)
 */
public class PalindromePairs {
    boolean isPal(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> hashMap = new HashMap<>();
        Set<Integer> included = new HashSet<>();
        List<List<Integer>> result = new LinkedList<>();
        IntStream.range(0, words.length).forEach(i -> {
            String reversed = new StringBuilder(words[i]).reverse().toString();
            hashMap.put(reversed, i);
        });
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            if (word.length() == 0) continue;
            StringBuilder builder = new StringBuilder();
            if (isPal(word, 0, word.length() - 1)) {
                if (hashMap.containsKey("")) {
                    int index = hashMap.get("");
                    int hash = index * words.length + j;
                    if (!included.contains(hash)) {
                        result.add(Arrays.asList(index, j));
                    }
                    hash = j * words.length + index;
                    if (!included.contains(hash)) {
                        result.add(Arrays.asList(j, index));
                    }
                }
            }
            for (int i = 0; i < word.length(); i++) {
                builder.append(word.charAt(i));
                String key = builder.toString();
                if (hashMap.containsKey(key)
                        && isPal(word, i + 1, word.length() - 1)) {
                    int index = hashMap.get(key);
                    int hash = index * words.length + j;
                    if (index == j || included.contains(hash)) break;
                    included.add(hash);
                    result.add(Arrays.asList(j, index));
                }
            }
            builder.setLength(0);
            for (int i = word.length() - 1; i >= 0; i--) {
                builder.insert(0, word.charAt(i));
                String key = builder.toString();
                if (hashMap.containsKey(key)
                        && isPal(word, 0, i - 1)) {
                    int index = hashMap.get(key);
                    int hash = j * words.length + index;
                    if (index == j || included.contains(hash)) break;
                    included.add(hash);
                    result.add(Arrays.asList(index, j));
                }
            }

        }
        return result;
    }

    public void test() {
        String [] array = new String[]{"abcd","dcba","lls","s","sssll"};
        List<List<Integer>> result = palindromePairs(array);
        for (List<Integer> pair : result) {
            System.out.print(pair.toString() + " ");
        }
    }
}
