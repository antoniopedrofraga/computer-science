/**
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Example 2:
 *
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 *
 *
 * Approach: (Go bottom up, if sum of the current path is less than 0, then restart, else compare left and right side). Apply Kadane’s algorithm, but in graphs.
 *
 * Time complexity: O(n)
 * Memory complexity: O(h), where h is the height of the tree.
 *
 * The response array in the recursive function will be composed by:
 *
 * { currentSum, maxSum, maxValue (for negative values only) }
 */

public class MaxPathSum {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int maxPathSum(TreeNode root) {
        int[] result = recursiveFind(root);
        return result[1] == 0 ? result[2] : result[1];
    }

    int[] recursiveFind(TreeNode root) {
        int currentSum = 0, maxSum = 0, sum = root.val, maxValue = root.val;
        if (root.left != null) {
            int[] left = recursiveFind(root.left);
            maxSum = Math.max(maxSum, left[1]);
            currentSum = Math.max(currentSum, left[0] + root.val);
            maxValue = Math.max(maxValue, left[2]);
            sum += left[0];
        }
        if (root.right != null) {
            int[] right = recursiveFind(root.right);
            maxSum = Math.max(maxSum, right[1]);
            currentSum = Math.max(currentSum, right[0] + root.val);
            maxValue = Math.max(maxValue, right[2]);
            sum += right[0];
        }
        currentSum = Math.max(currentSum, root.val);
        maxSum = Math.max(maxSum, sum);
        return new int[]{currentSum, maxSum, maxValue};
    }

    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));
    }
}
