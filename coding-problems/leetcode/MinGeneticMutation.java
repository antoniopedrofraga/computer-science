import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 *
 * Note:
 *
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 *
 *
 * Example 1:
 *
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * return: 1
 *
 *
 * Example 2:
 *
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * return: 2
 *
 *
 * Example 3:
 *
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * return: 3
 *
 * Approach: Run a breadth first search, which is suitable for finding min paths in unweighted graphs.
 *
 * Time complexity: O(n^2)
 * Memory complexity: O(n)
 *
 */

public class MinGeneticMutation {
    int differs(String a, String b) {
        int diff = Math.abs(a.length() - b.length()), count = 0;
        if (diff > 1) return diff;
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count + Math.abs(a.length() - b.length());
    }

    class Node {
        String s;
        int level;
        Node(String s, int level) {
            this.s = s;
            this.level = level;
        }
    }

    public int minMutation(String start, String end, String[] bank) {
        Set<String> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited.add(start);
        while (!q.isEmpty()) {
            Node root = q.remove();
            String s = root.s;
            if (s.equals(end)) return root.level;
            for (String a : bank) {
                if (!visited.contains(a) && differs(s, a) == 1) {
                    visited.add(a);
                    q.add(new Node(a, root.level + 1));
                }
            }
        }
        return -1;
    }

}
