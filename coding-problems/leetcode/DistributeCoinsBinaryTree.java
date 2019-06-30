/**
 * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
 *
 * Return the number of moves required to make every node have exactly one coin.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 * Example 2:
 *
 *
 *
 * Input: [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
 * Example 3:
 *
 *
 *
 * Input: [1,0,2]
 * Output: 2
 * Example 4:
 *
 *
 *
 * Input: [1,0,0,null,3]
 * Output: 4
 *
 *
 * Note:
 *
 * 1<= N <= 100
 * 0 <= node.val <= N
 * Approach: Count the number of moves to do in every level. If we need to make moves to there they will be negative. We just need to add the absolute value of it to our total (because we know we will need to move things there anyway).
 *
 * Time complexity: O(n)
 * Memory complexity: O(h)
 *
 * Our result array in each level will be formatted as: {total, excess}
 *
 */

public class DistributeCoinsBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int distributeCoins(TreeNode root) {
        int[] result = recursiveSolve(root);
        return result[0];
    }

    int[] recursiveSolve(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = recursiveSolve(root.left);
        int[] right = recursiveSolve(root.right);
        int total = left[0] + right[0];
        int moves = root.val + left[1] + right[1] - 1;
        total += Math.abs(moves);
        return new int[]{total, moves};
    }

}
