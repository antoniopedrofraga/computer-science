import java.util.*;

/**
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Your job is to implement the following functions:
 *
 * The constructor function:
 *
 * AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
 *
 * Now, the user wants to input a new sentence. The following function will provide the next character the user types:
 *
 * List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 *
 *
 * Example:
 * Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * The system have already tracked down the following sentences and their corresponding times:
 * "i love you" : 5 times
 * "island" : 3 times
 * "ironman" : 2 times
 * "i love leetcode" : 2 times
 * Now, the user begins another search:
 *
 * Operation: input('i')
 * Output: ["i love you", "island","i love leetcode"]
 * Explanation:
 * There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 *
 * Operation: input(' ')
 * Output: ["i love you","i love leetcode"]
 * Explanation:
 * There are only two sentences that have prefix "i ".
 *
 * Operation: input('a')
 * Output: []
 * Explanation:
 * There are no sentences that have prefix "i a".
 *
 * Operation: input('#')
 * Output: []
 * Explanation:
 * The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 *
 * Note:
 *
 * The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
 * The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
 * Please use double-quote instead of single-quote when you write test cases even for a character input.
 * Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * Time complexity build: O(s) (s being sum off all sentences size)
 * Time complexity input : O(q) (q being the query size)
 * Memory complexity to hold tree: O(n)
 */
class AutocompleteSystem {
    public AutocompleteSystem() {}

    class Wrapper implements Comparable<Wrapper> {
        String sentence;
        int times;
        public Wrapper(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }
        @Override
        public int compareTo(Wrapper w2) {
            if (this.times == w2.times)
                return w2.sentence.compareTo(sentence);
            return this.times - w2.times;
        }
        @Override
        public boolean equals(Object obj) {
            Wrapper w2 = (Wrapper) obj;
            return this.sentence.equals(w2.sentence);
        }
    }

    class TrieNode {
        PriorityQueue<Wrapper> top = new PriorityQueue<>();
        Map<Character, TrieNode> childs = new HashMap<>();
        int freq = 0;

        private void updateTop(Wrapper wrapper) {
            top.remove(wrapper);
            top.add(wrapper);
            if (top.size() > 3) top.remove();
        }
        public void insert(Wrapper wrapper, int i) {
            String sentence = wrapper.sentence;
            if (sentence.length() <= i) {
                freq += wrapper.times;
                wrapper.times = freq;
                updateTop(wrapper);
                return;
            }
            childs.putIfAbsent(sentence.charAt(i), new TrieNode());
            childs.get(sentence.charAt(i)).insert(wrapper, i + 1);
            updateTop(wrapper);
        }

        public List<String> query(String query, int i) {
            if (i == query.length()) {
                PriorityQueue<Wrapper> toEmpty = new PriorityQueue<>(top);
                List<String> toReturn = new ArrayList<>();
                while (!toEmpty.isEmpty()) {
                    toReturn.add(toEmpty.remove().sentence);
                }
                Collections.reverse(toReturn);
                return toReturn;
            }
            if (!childs.containsKey(query.charAt(i)))
                return Collections.emptyList();
            return childs.get(query.charAt(i)).query(query, i + 1);
        }
    }

    TrieNode head = new TrieNode();
    StringBuilder query = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            head.insert(new Wrapper(sentences[i], times[i]), 0);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            head.insert(new Wrapper(query.toString(), 1), 0);
            query.setLength(0);
            return Collections.emptyList();
        }
        query.append(c);
        return head.query(query.toString(), 0);
    }

    public void test() {
        String [] cmds = new String[]{"AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"};
        Object [] args = new Object[]{new Object[] {new String[]{"i love you","island","iroman","i love leetcode"},new int[]{5,3,2,2}},
                new String [] {"i"},  new String [] {" "},  new String [] {"a"},  new String [] {"#"},  new String [] {"i"},  new String [] {" "},
                new String [] {"a"},  new String [] {"#"},  new String [] {"i"},  new String [] {" "},  new String [] {"a"},  new String [] {"#"}};
        AutocompleteSystem search = new AutocompleteSystem();
        for (int i = 0; i < cmds.length; i++) {
            if (cmds[i].equals("AutocompleteSystem")) {
                String [] sentences = (String[])((Object []) args[i])[0];
                int [] times = (int [])((Object []) args[i])[1];
                search = new AutocompleteSystem(sentences, times);
            } else {
                char c = ((String[]) args[i])[0].charAt(0);
                System.out.println(search.input(c));
            }
        }
    }
}

