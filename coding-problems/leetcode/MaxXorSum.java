/**
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * Could you do this in O(n) runtime?
 *
 * Example:
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 *
 *
 * Approach: Create a trie.
 * Time complexity: O(n)
 * Memory complexity: O(1) Worst case you have 2^32 nodes
 */
public class MaxXorSum {
    class TrieNode {
        TrieNode [] children = new TrieNode[2];
    }

    void insert(TrieNode node, int num, int bit) {
        if (bit < 0) return;
        int currBit = (num >> bit) & 1;
        if (node.children[currBit] == null) {
            node.children[currBit] = new TrieNode();
        }
        insert(node.children[currBit], num, bit - 1);
    }

    int getMax(TrieNode node, int num, int bit) {
        if (bit < 0) return 0;
        int currBit = (num >> bit) & 1, index;
        if (node.children[0] == null) {
            index = 1;
        } else if (node.children[1] == null) {
            index = 0;
        } else {
            index = currBit == 0 ? 1 : 0;
        }
        int value = (currBit ^ index) == 1 ? (int) Math.pow(2, bit) : 0;
        return value + getMax(node.children[index], num,  bit - 1);
    }

    public int findMaximumXOR(int[] nums) {
        TrieNode head = new TrieNode();
        int max = 0;
        for (int num : nums) {
            insert(head, num, 31);
        }
        for (int num : nums) {
            max = Math.max(max, getMax(head, num, 31));
        }
        return max;
    }

    public void test() {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
    }

}
