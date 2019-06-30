import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Please use this Google doc to code during your interview. To free your hands for coding, we recommend that you use a headset or a phone with speaker option.
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 * Example 1:
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * Example 2:
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 *   Time Complexity: O(n)
 *   Memory Complexity: O(n)
 */
public class RecoverBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    TreeNode temp1 = null, temp2 = null;
    TreeNode previous = null;

    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = temp1.val;
        temp1.val = temp2.val;
        temp2.val = temp;
    }

    public void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (previous != null && temp1 == null && root.val <= previous.val) {
            temp1 = previous;
        }
        if (previous != null && temp1 != null && root.val <= previous.val) {
            temp2 = root;
        }
        traverse(root.right);
        previous = root;
    }

}
