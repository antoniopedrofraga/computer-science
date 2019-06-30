/**
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Note: The length of path between two nodes is represented by the number of edges between them.
 *
 *
 * Approach: Use recursion to get the deepest path at any level. We should the include the max diameter as well.
 *
 * Time complexity: O(n)
 * Memory complexity: O(h) (h being the height of the binary tree)
 */

public class DiamaterBinaryTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int [] result = dfs(root);
        return result[1];
    }

    int [] dfs(TreeNode root) {
        int maxPath = 0, diameter = 0, maxDiamater = 0;
        if (root.left != null) {
            int [] left = dfs(root.left);
            maxPath = Math.max(left[0], maxPath);
            diameter += left[0];
            maxDiamater = Math.max(maxDiamater, left[1]);
        }
        if (root.right != null) {
            int [] right = dfs(root.right);
            maxPath = Math.max(right[0], maxPath);
            diameter += right[0];
            maxDiamater = Math.max(maxDiamater, right[1]);
        }
        maxDiamater = Math.max(maxDiamater, diameter);
        return new int[]{maxPath + 1, maxDiamater};
    }

    public void test() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root));
    }
}
