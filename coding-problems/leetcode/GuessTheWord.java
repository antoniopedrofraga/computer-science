import java.util.*;

/**
 * This problem is an interactive problem new to the LeetCode platform.
 *
 * We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
 *
 * You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.
 *
 * This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
 *
 * For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
 *
 * Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.
 *
 * Approach: We can first start by guessing a random word and reducing the problem from there.
 * Let's say we try to guess word j, we can compare word j with word i and check if we have the same number of matches. If we do, it is a possible match!!
 * The second idea is to apply minimax in order to converge to a solution faster.
 */

public class GuessTheWord {
    interface Master {
      public int guess(String word);
    }
    int matches(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i))
                matches++;
        }
        return matches;
    }
    public void findSecretWord(String[] wordlist, Master master) {
        Random random = new Random();
        List<String> list = new ArrayList<>(Arrays.asList(wordlist));
        for (int i = 0, matchesCount = 0; i < 10 && matchesCount < 6; i++) {
            String minWord = list.get(random.nextInt(list.size()));
            int min = Integer.MAX_VALUE;
            Map<String, Integer> memo = new HashMap<>();
            for (int k = 0; k < list.size(); k++) {
                for (int j = k + 1; j < list.size(); j++) {
                    if (matches(list.get(k), list.get(j)) == 0) {
                        memo.put(list.get(k), memo.getOrDefault(list.get(k), 0) + 1);
                        memo.put(list.get(j), memo.getOrDefault(list.get(j), 0) + 1);
                    }
                }
            }
            for (Map.Entry<String, Integer> entry : memo.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();
                if (count < min) {
                    minWord = word;
                    min = count;
                }
            }
            matchesCount = master.guess(minWord);
            List<String> newList = new ArrayList<>();
            for (String word : list) {
                if (matches(minWord, word) == matchesCount) {
                    newList.add(word);
                }
            }
            list = newList;
        }
    }

    public void test() {
        findSecretWord(new String[]{"acckzz","ccbazz","eiowzz","abcczz"}, null);
    }
}
