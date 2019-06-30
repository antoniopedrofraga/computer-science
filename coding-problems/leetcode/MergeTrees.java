/**
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
 *
 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
 *
 * Example 1:
 *
 * Input:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * Output:
 * Merged tree:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 *
 * Note: The merging process must start from the root nodes of both trees.
 *
 */
public class MergeTrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        TreeNode subRoot = new TreeNode(0);
        if (t1 != null) subRoot.val += t1.val;
        if (t2 != null) subRoot.val += t2.val;
        subRoot.left = mergeTrees(t1 != null ? t1.left : null, t2 != null ? t2.left : null);
        subRoot.right = mergeTrees(t1 != null ? t1.right : null, t2 != null ? t2.right : null);
        return subRoot;
    }
}
