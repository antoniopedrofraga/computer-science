import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 *
 * Example:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 *
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 *
 * Approach: I’ve implemented both Karp Rabin algorithm (that would be suitable for patterns with variable length), and a simple substring method. In this case we have a fixed pattern length, so it won't matter.
 *
 * Time complexity: O(n)
 * Memory complexity: O(n)
 */
public class RepeatedDNASeq {
    final static int RADIX = 256, PRIME = 683303;
    int RM = 1;

    int addCharHash(int hash, char c) {
        return (hash * RADIX + c) % PRIME;
    }
    int remCharHash(int hash, char c) {
        return (hash + PRIME - RM * c % PRIME) % PRIME;
    }
    void buildCountMap(Map<Integer, Integer> count, String s) {
        Queue<Character> q = new LinkedList<>();
        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash = addCharHash(hash, s.charAt(i));
            q.add(s.charAt(i));
        }
        count.put(hash, 1);
        for (int i = 10; i < s.length(); i++) {
            char removed = q.remove(), inserted = s.charAt(i);
            hash = remCharHash(hash, removed);
            hash = addCharHash(hash, inserted);
            q.add(inserted);
            count.put(hash, count.getOrDefault(hash, 0) + 1);
        }
    }
    void solveCollisions(Map<Integer, Integer> count, Set<String> result, Map<Integer, List<String>> lists, Queue<Character> q, int hash) {
        if (count.get(hash) > 1) {
            lists.putIfAbsent(hash, new ArrayList<>());
            List<String> collisions = lists.get(hash);
            Queue<Character> clone = new LinkedList<>(q);
            StringBuilder builder = new StringBuilder();
            while (!clone.isEmpty()) {
                builder.append(clone.remove());
            }
            if (collisions.contains(builder.toString())) {
                result.add(builder.toString());
            } else {
                collisions.add(builder.toString());
            }

        }
    }
    List<String> findRepeated(Map<Integer, Integer> count, String s) {
        Set<String> result = new HashSet<>();
        Map<Integer, List<String>> lists = new HashMap<>();
        Queue<Character> q = new LinkedList<>();
        int hash = 0;
        for (int i = 0; i < 10; i++) {
            hash = addCharHash(hash, s.charAt(i));
            q.add(s.charAt(i));
        }
        solveCollisions(count, result, lists, q, hash);
        for (int i = 10; i < s.length(); i++) {
            char removed = q.remove(), inserted = s.charAt(i);
            hash = remCharHash(hash, removed);
            hash = addCharHash(hash, inserted);
            q.add(inserted);
            solveCollisions(count, result, lists, q, hash);
        }
        return new ArrayList<>(result);
    }
    public List<String> findRepeatedDnaSequencesKarpRabin(String s) {
        if (s.length() < 11) return Collections.emptyList();
        for (int i = 1; i <= 9; i++)
            RM = (RADIX * RM) % PRIME;
        Map<Integer, Integer> count = new HashMap<>();
        buildCountMap(count, s);
        return findRepeated(count, s);
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>(), repeated = new HashSet<>();
        for (int i = 0; i + 9 < s.length(); i++) {
            String substring = s.substring(i, i + 10);
            if (seen.contains(substring))
                repeated.add(substring);
            else
                seen.add(substring);
        }
        return new ArrayList<>(repeated);
    }

    public void test() {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT").toString());
    }
}
