import java.util.TreeMap;

/**
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
 *
 * Return true if and only if she can.
 *
 *
 *
 * Example 1:
 *
 * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
 * Output: true
 * Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
 * Example 2:
 *
 * Input: hand = [1,2,3,4,5], W = 4
 * Output: false
 * Explanation: Alice's hand can't be rearranged into groups of 4.
 *
 *
 * Note:
 *
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 */
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        for (int value : hand) {
            bst.put(value, bst.getOrDefault(value, 0) + 1);
        }
        while (!bst.isEmpty()) {
            int num = bst.firstKey();
            for (int j = 0; j < W; j++) {
                Integer occur = bst.get(num);
                if (occur == null) return false;
                if (occur - 1 > 0) {
                    bst.put(num, occur - 1);
                } else {
                    bst.remove(num);
                }
                num++;
            }
        }
        return true;
    }

    public void test() {
        System.out.println(isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8}, 3));
        System.out.println(isNStraightHand(new int[]{1,2,3,4,5}, 4));
    }
}
