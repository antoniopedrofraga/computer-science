import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 *
 * Approach: Do it iteratively.
 * Time complexity: O(n)
 * Memory complexity: O(h)
 *
 */
public class InorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.peek();
            if (top.left != null) {
                stack.push(top.left);
                top.left = null;
                continue;
            }
            list.add(top.val);
            stack.pop();
            if (top.right != null) {
                stack.push(top.right);
                top.right = null;
            }
        }
        return list;
    }

}
